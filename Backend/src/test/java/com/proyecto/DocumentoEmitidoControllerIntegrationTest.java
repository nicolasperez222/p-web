package com.proyecto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.proyecto.dto.DocumentoEmitidoDTO;
import com.proyecto.dto.JwtAuthenticationResponse;
import com.proyecto.dto.LoginDTO;
import com.proyecto.model.*;
import com.proyecto.repository.*;

@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentoEmitidoControllerIntegrationTest {

    private String BASE_URL;
    @LocalServerPort
    private int port;

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired private ResolucionRepository resolucionRepository;
    @Autowired private DocumentoEmitidoRepository documentoEmitidoRepository;

    @Autowired
    private TestRestTemplate rest;

    @BeforeEach
    void init() {
        BASE_URL = "http://localhost:" + port;

        if (!BASE_URL.endsWith("/")) {
            BASE_URL = BASE_URL + "/";
        }

        userRepository.save(new User("Admin", "Admin", "admin@test.com",
                passwordEncoder.encode("1234"), Role.ADMIN));

        userRepository.save(new User("User", "User", "user@test.com",
                passwordEncoder.encode("1234"), Role.USER));

        Cliente cli = new Cliente();
        cli.setRazonSocial("Cliente 1");
        cli = clienteRepository.save(cli);

        TipoDocumento tipo = new TipoDocumento();
        tipo.setNombre("Factura");
        tipoDocumentoRepository.save(tipo);

        Resolucion res = new Resolucion();
        res.setPrefijo("FV");
        resolucionRepository.save(res);

        DocumentoEmitido doc = new DocumentoEmitido();
        doc.setCliente(cli);
        doc.setTipoDocumento(tipo);
        doc.setResolucion(res);
        doc.setNumero("001");
        doc.setTotal(new BigDecimal("10000"));
        documentoEmitidoRepository.save(doc);
    }

    private JwtAuthenticationResponse login(String email, String pass) {

        RequestEntity<LoginDTO> request = RequestEntity
                .post(BASE_URL + "/auth/login")
                .body(new LoginDTO(email, pass));

        ResponseEntity<JwtAuthenticationResponse> response =
                rest.exchange(request, JwtAuthenticationResponse.class);

        assertNotNull(response.getBody());
        return response.getBody();
    }


    @Test
    void getDocumentoSinLogin() {

        RequestEntity<Void> request = RequestEntity
                .get(BASE_URL + "/api/documentos-emitidos/1")
                .build();

        ResponseEntity<String> response = rest.exchange(request, String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }


    @Test
    void getDocumentoConUsuario() {

        JwtAuthenticationResponse user = login("user@test.com", "1234");

        RequestEntity<Void> request = RequestEntity
                .get(BASE_URL + "/api/documentos-emitidos/1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + user.getToken())
                .build();

        ResponseEntity<DocumentoEmitidoDTO> response =
                rest.exchange(request, DocumentoEmitidoDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("001", response.getBody().getNumero());
    }


    @Test
    void getTodosLosDocumentosAdmin() {

        JwtAuthenticationResponse admin = login("admin@test.com", "1234");

        RequestEntity<Void> request = RequestEntity
                .get(BASE_URL + "/api/documentos-emitidos")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + admin.getToken())
                .build();

        ResponseEntity<DocumentoEmitidoDTO[]> response =
                rest.exchange(request, DocumentoEmitidoDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length >= 1);
    }


    @Test
    void crearDocumentoAdmin() {

        JwtAuthenticationResponse admin = login("admin@test.com", "1234");

        DocumentoEmitidoDTO dto = new DocumentoEmitidoDTO();
        dto.setClienteId(1);
        dto.setTipoDocumentoId(1);
        dto.setResolucionId(1);
        dto.setNumero("002");
        dto.setTotal(new BigDecimal("5000"));

        RequestEntity<DocumentoEmitidoDTO> request = RequestEntity
                .post(BASE_URL + "/api/documentos-emitidos")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + admin.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);

        ResponseEntity<DocumentoEmitidoDTO> response =
                rest.exchange(request, DocumentoEmitidoDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("002", response.getBody().getNumero());
    }


    @Test
    void eliminarDocumentoAdmin() {

        JwtAuthenticationResponse admin = login("admin@test.com", "1234");

        RequestEntity<Void> request = RequestEntity
                .delete(BASE_URL + "/api/documentos-emitidos/1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + admin.getToken())
                .build();

        ResponseEntity<Void> response =
                rest.exchange(request, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


    @Test
    void getDocumentosPorCliente() {
        JwtAuthenticationResponse user = login("user@test.com", "1234");

        RequestEntity<Void> request = RequestEntity
                .get(BASE_URL + "/api/documentos-emitidos/cliente/1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + user.getToken())
                .build();

        ResponseEntity<DocumentoEmitidoDTO[]> response =
                rest.exchange(request, DocumentoEmitidoDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
