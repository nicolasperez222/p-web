package com.proyecto;

import com.proyecto.dto.ClienteDTO;
import com.proyecto.dto.JwtAuthenticationResponse;
import com.proyecto.dto.LoginDTO;
import com.proyecto.model.*;
import com.proyecto.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIT {

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate rest;

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;

    @Autowired private ClienteRepository clienteRepo;
    @Autowired private DepartamentoRepository depRepo;
    @Autowired private MunicipioRepository munRepo;

    private Departamento dep;
    private Municipio mun;

    private String baseUrl() {
        return "http://localhost:" + port + "/api/clientes";
    }


    private JwtAuthenticationResponse login(String email, String pass) {
        LoginDTO body = new LoginDTO(email, pass);

        ResponseEntity<JwtAuthenticationResponse> response = rest.postForEntity(
                "http://localhost:" + port + "/auth/login",
                body,
                JwtAuthenticationResponse.class
        );

        assertThat(response.getBody()).isNotNull();
        return response.getBody();
    }

    private HttpHeaders authAdmin() {
        JwtAuthenticationResponse admin = login("admin@test.com", "1234");
        HttpHeaders h = new HttpHeaders();
        h.set(HttpHeaders.AUTHORIZATION, "Bearer " + admin.getToken());
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }

    private HttpHeaders authUser() {
        JwtAuthenticationResponse user = login("user@test.com", "1234");
        HttpHeaders h = new HttpHeaders();
        h.set(HttpHeaders.AUTHORIZATION, "Bearer " + user.getToken());
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }

    @BeforeEach
    void setup() {

        userRepo.save(new User("Admin", "Admin", "admin@test.com",
                encoder.encode("1234"), Role.ADMIN));

        userRepo.save(new User("User", "User", "user@test.com",
                encoder.encode("1234"), Role.USER));

        dep = new Departamento();
        dep.setNombre("Cundinamarca");
        dep = depRepo.save(dep);

        mun = new Municipio();
        mun.setNombre("Bogotá");
        mun = munRepo.save(mun);
    }


    @Test
    void adminPuedeCrearYListarClientes() {

        ClienteDTO dto = new ClienteDTO();
        dto.setNit("123");
        dto.setDv("1");
        dto.setRazonSocial("Cliente Admin");
        dto.setDepartamentoId(dep.getId());
        dto.setMunicipioId(mun.getId());

        HttpEntity<ClienteDTO> req = new HttpEntity<>(dto, authAdmin());

        ResponseEntity<String> response =
                rest.postForEntity(baseUrl(), req, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<ClienteDTO[]> list = rest.exchange(
                baseUrl(),
                HttpMethod.GET,
                new HttpEntity<>(authAdmin()),
                ClienteDTO[].class
        );

        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody()).hasSize(1);
    }

    @Test
    void adminPuedeEliminarCliente() {

        Cliente c = new Cliente();
        c.setNit("999");
        c.setDv("9");
        c.setRazonSocial("Cliente X");
        c.setDepartamento(dep);
        c.setMunicipio(mun);
        c = clienteRepo.save(c);

        ResponseEntity<Void> result = rest.exchange(
                baseUrl() + "/" + c.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(authAdmin()),
                Void.class
        );

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(clienteRepo.existsById(c.getId())).isFalse();
    }

    @Test
    void userNoPuedeListarClientes() {

        ResponseEntity<String> response = rest.exchange(
                baseUrl(),
                HttpMethod.GET,
                new HttpEntity<>(authUser()),
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void userNoPuedeCrearClientes() {

        ClienteDTO dto = new ClienteDTO();
        dto.setNit("55");
        dto.setDv("5");
        dto.setRazonSocial("No User");
        dto.setDepartamentoId(dep.getId());
        dto.setMunicipioId(mun.getId());

        ResponseEntity<String> response = rest.postForEntity(
                baseUrl(),
                new HttpEntity<>(dto, authUser()),
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void userNoPuedeEliminarClientes() {

        Cliente c = new Cliente();
        c.setNit("111");
        c.setDv("1");
        c.setRazonSocial("Cliente Bloq");
        c.setDepartamento(dep);
        c.setMunicipio(mun);
        c = clienteRepo.save(c);

        ResponseEntity<Void> response = rest.exchange(
                baseUrl() + "/" + c.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(authUser()),
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
