package com.proyecto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.proyecto.dto.*;
import com.proyecto.model.*;
import com.proyecto.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTest {

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate rest;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder encoder;
    @Autowired private DepartamentoRepository departamentoRepository;
    @Autowired private MunicipioRepository municipioRepository;
    @Autowired private TipoDocumentoRepository tipoDocumentoRepository;

    private Integer tipoDocId;

    private String BASE_URL;
    private String adminToken;
    private String userToken;

    @BeforeEach
    void setup() {

        BASE_URL = "http://localhost:" + port + "/";

        // ----- Datos base -----
        Departamento dep = new Departamento();
        dep.setNombre("BOGOTA");
        departamentoRepository.save(dep);

        Municipio muni = new Municipio();
        muni.setNombre("Bogotá D.C.");
        municipioRepository.save(muni);
        TipoDocumento td = new TipoDocumento();
        td.setNombre("FACTURA");
        tipoDocumentoRepository.save(td);
        tipoDocId = td.getIdTipoDocumento();
        // ----- Usuarios -----
        User admin = new User();
        admin.setEmail("admin@admi.com");
        admin.setPassword(encoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        User user = new User();
        user.setEmail("user@user.com");
        user.setPassword(encoder.encode("user"));
        user.setRole(Role.USER);
        userRepository.save(user);

        // ----- Login -----
        adminToken = login("admin@admi.com", "admin");
        userToken  = login("user@user.com", "user");
    }

    /** Realiza login y devuelve token */
    private String login(String email, String pass) {

        LoginDTO dto = new LoginDTO(email, pass);

        RequestEntity<LoginDTO> req = RequestEntity
                .post(BASE_URL + "auth/login")
                .body(dto);

        ResponseEntity<JwtAuthenticationResponse> resp =
                rest.exchange(req, JwtAuthenticationResponse.class);

        assertNotNull(resp.getBody(), "Login no retornó cuerpo");
        assertNotNull(resp.getBody().getToken(), "Token no recibido");

        return resp.getBody().getToken();
    }

    /** Headers autenticados ADMIN */
    private HttpHeaders adminHeaders() {
        HttpHeaders h = new HttpHeaders();
        h.setBearerAuth(adminToken);
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }

    /** Headers autenticados USER */
    private HttpHeaders userHeaders() {
        HttpHeaders h = new HttpHeaders();
        h.setBearerAuth(userToken);
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }


    // ========================================================
    //                     TEST PRINCIPAL
    // ========================================================

    @Test
    void flujoCompletoSistema() {

        // ----------------------------------------------------
        // 1. ADMIN crea cliente
        // ----------------------------------------------------
        ClienteDTO cli = new ClienteDTO();
        cli.setRepresentanteLegal("Juan Pérez");
        cli.setRazonSocial("Cliente Demo S.A.S");
        cli.setNit("900100200");
        cli.setDv("1");
        cli.setEmail("demo@correo.com");
        cli.setTelefono("3001234567");
        cli.setDireccion("Calle 123 #45-67");
        cli.setTipoEmpresa(1);
        cli.setResponsabilidadTributaria("REGIMEN SIMPLE");
        cli.setRegimenIva("COMÚN");
        cli.setCodigoCiiu("6201");
        cli.setImpuesto("IVA");
        cli.setEstado(1);
        cli.setDepartamentoId(1);
        cli.setMunicipioId(1);

        RequestEntity<ClienteDTO> reqCliente = RequestEntity
                .post(BASE_URL + "api/clientes")
                .headers(adminHeaders())
                .body(cli);

        ResponseEntity<String> respCliente =
                rest.exchange(reqCliente, String.class);

        assertEquals(HttpStatus.OK, respCliente.getStatusCode());


        // ----------------------------------------------------
        // 2. ADMIN crea resolución
        // ----------------------------------------------------
        ResolucionDTO res = new ResolucionDTO();
        res.setClienteId(1);
        res.setTipoDocumentoId(1);
        res.setNumeroResolucion("R-123");
        res.setPrefijo("FAC");
        res.setFechaCreacion(java.sql.Date.valueOf(LocalDate.now()));
        res.setDesde(1);
        res.setHasta(9999);


        RequestEntity<ResolucionDTO> reqRes = RequestEntity
                .post(BASE_URL + "api/resoluciones")
                .headers(adminHeaders())
                .body(res);

        ResponseEntity<String> respRes =
                rest.exchange(reqRes, String.class);

        assertEquals(HttpStatus.OK, respRes.getStatusCode());

        RequestEntity<Void> reqListRes = RequestEntity
                .get(BASE_URL + "api/resoluciones")
                .headers(adminHeaders())
                .build();

        ResponseEntity<List<ResolucionDTO>> resoluciones =
                rest.exchange(reqListRes,
                        new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, resoluciones.getStatusCode());
        assertFalse(resoluciones.getBody().isEmpty());

        Integer resolucionId = resoluciones.getBody()
                .get(resoluciones.getBody().size() - 1)
                .getIdResolucion();

        // 3. ADMIN crea documento
        DocumentoEmitidoDTO doc = new DocumentoEmitidoDTO();
        doc.setClienteId(1);
        doc.setNumero("FAC-0001");
        doc.setResolucionId(resolucionId);
        doc.setTipoDocumentoId(tipoDocId); 

        doc.setFecha(java.sql.Timestamp.valueOf("2025-01-01 10:00:00"));
        doc.setValorBruto(new BigDecimal("10000"));
        doc.setDescuentos(BigDecimal.ZERO);
        doc.setSubtotal(new BigDecimal("10000"));
        doc.setImpuestos(new BigDecimal("1900"));
        doc.setTotal(new BigDecimal("11900"));


        RequestEntity<DocumentoEmitidoDTO> reqDoc = RequestEntity
                .post(BASE_URL + "api/documentos-emitidos")
                .headers(adminHeaders())
                .body(doc);

        ResponseEntity<DocumentoEmitidoDTO> respDoc =
                rest.exchange(reqDoc, DocumentoEmitidoDTO.class);

        assertEquals(HttpStatus.OK, respDoc.getStatusCode());
        assertNotNull(respDoc.getBody());

        Integer documentoId = respDoc.getBody().getIdDocumento();
        assertNotNull(documentoId);

        // 4. USER consulta documentos
        RequestEntity<Void> reqDocsUser = RequestEntity
                .get(BASE_URL + "api/documentos-emitidos")
                .headers(userHeaders())
                .build();

        ResponseEntity<List<DocumentoEmitidoDTO>> listaUser =
                rest.exchange(reqDocsUser,
                        new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, listaUser.getStatusCode());
        assertFalse(listaUser.getBody().isEmpty());


        // 5. USER intenta crear documento 
        RequestEntity<DocumentoEmitidoDTO> reqUserCreate = RequestEntity
                .post(BASE_URL + "api/documentos-emitidos")
                .headers(userHeaders())
                .body(doc);

        ResponseEntity<String> respUserForbidden =
                rest.exchange(reqUserCreate, String.class);

        assertEquals(HttpStatus.FORBIDDEN, respUserForbidden.getStatusCode());

        // 6. ADMIN elimina documento
        RequestEntity<Void> reqDelete = RequestEntity
                .delete(BASE_URL + "api/documentos-emitidos/" + documentoId)
                .headers(adminHeaders())
                .build();

        ResponseEntity<Void> respDelete =
                rest.exchange(reqDelete, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, respDelete.getStatusCode());


        // 7. USER intenta consultar documento eliminado
        RequestEntity<Void> reqGetDeleted = RequestEntity
                .get(BASE_URL + "api/documentos-emitidos/" + documentoId)
                .headers(userHeaders())
                .build();

        ResponseEntity<DocumentoEmitidoDTO> respDeleted =
                rest.exchange(reqGetDeleted, DocumentoEmitidoDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, respDeleted.getStatusCode());
    }
}
