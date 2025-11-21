package com.proyecto;

import com.proyecto.dto.JwtAuthenticationResponse;
import com.proyecto.dto.LoginDTO;
import com.proyecto.dto.ResolucionDTO;
import com.proyecto.mapper.ResolucionMapper;
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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResolucionControllerIT {

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate rest;

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;

    @Autowired private ClienteRepository clienteRepo;
    @Autowired private TipoDocumentoRepository tipoRepo;
    @Autowired private ResolucionRepository resolucionRepo;

    private Cliente cliente;
    private TipoDocumento tipoDoc;

    private String baseUrl() {
        return "http://localhost:" + port + "/api/resoluciones";
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

       
        cliente = new Cliente();
        cliente.setRazonSocial("Cliente Demo");
        cliente = clienteRepo.save(cliente);

       
        tipoDoc = new TipoDocumento();
        tipoDoc.setNombre("FACTURA CONTINGENCIA");
        tipoDoc = tipoRepo.save(tipoDoc);
    }

    
    @Test
    void adminPuedeCrearYListarResoluciones() {

        ResolucionDTO dto = new ResolucionDTO();
        dto.setClienteId(cliente.getId());
        dto.setTipoDocumentoId(tipoDoc.getIdTipoDocumento());
        dto.setNumeroResolucion("ABC123");
        dto.setPrefijo("FV");
        dto.setFechaCreacion(java.sql.Date.valueOf(LocalDate.now()));
        dto.setDesde(1);
        dto.setHasta(1000);

        HttpEntity<ResolucionDTO> request = new HttpEntity<>(dto, authAdmin());

        ResponseEntity<ResolucionDTO> response =
                rest.postForEntity(baseUrl(), request, ResolucionDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getNumeroResolucion()).isEqualTo("ABC123");

       
        ResponseEntity<ResolucionDTO[]> list = rest.exchange(
                baseUrl(),
                HttpMethod.GET,
                new HttpEntity<>(authAdmin()),
                ResolucionDTO[].class
        );

        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody()).hasSize(1);
    }

    @Test
    void adminPuedeEliminarResolucion() {

        ResolucionDTO dto = new ResolucionDTO();
        dto.setClienteId(cliente.getId());
        dto.setTipoDocumentoId(tipoDoc.getIdTipoDocumento());
        dto.setNumeroResolucion("DEL1");
        dto.setPrefijo("XX");
        dto.setFechaCreacion(java.sql.Date.valueOf(LocalDate.now()));

        Resolucion entity = resolucionRepo.save(
                ResolucionMapper.toModel(dto, cliente, tipoDoc)
        );

        ResponseEntity<Void> result = rest.exchange(
                baseUrl() + "/" + entity.getIdResolucion(),
                HttpMethod.DELETE,
                new HttpEntity<>(authAdmin()),
                Void.class
        );

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(resolucionRepo.existsById(entity.getIdResolucion())).isFalse();
    }


    @Test
    void userPuedeListarResoluciones() {

        Resolucion r = new Resolucion();
        r.setCliente(cliente);
        r.setTipoDocumento(tipoDoc);
        r.setNumeroResolucion("USERTEST");
        resolucionRepo.save(r);

        ResponseEntity<ResolucionDTO[]> response = rest.exchange(
                baseUrl(),
                HttpMethod.GET,
                new HttpEntity<>(authUser()),
                ResolucionDTO[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void userNoPuedeCrearResolucion() {

        ResolucionDTO dto = new ResolucionDTO();
        dto.setClienteId(cliente.getId());
        dto.setTipoDocumentoId(tipoDoc.getIdTipoDocumento());
        dto.setNumeroResolucion("NOUSER");
        dto.setPrefijo("ZZ");
        dto.setFechaCreacion(java.sql.Date.valueOf(LocalDate.now()));

        HttpEntity<ResolucionDTO> request = new HttpEntity<>(dto, authUser());

        ResponseEntity<String> response = rest.postForEntity(
                baseUrl(), request, String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void userNoPuedeEliminarResolucion() {

        Resolucion r = new Resolucion();
        r.setCliente(cliente);
        r.setTipoDocumento(tipoDoc);
        r.setNumeroResolucion("X1");
        r = resolucionRepo.save(r);

        ResponseEntity<Void> response = rest.exchange(
                baseUrl() + "/" + r.getIdResolucion(),
                HttpMethod.DELETE,
                new HttpEntity<>(authUser()),
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
