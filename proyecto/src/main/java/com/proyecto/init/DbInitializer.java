package com.proyecto.init;

import com.proyecto.model.Departamento;
import com.proyecto.model.Municipio;
import com.proyecto.repository.DepartamentoRepository;
import com.proyecto.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public void run(String... args) throws Exception {

      
        Departamento cundinamarca = new Departamento();
        cundinamarca.setNombre("Cundinamarca");
        departamentoRepository.save(cundinamarca);

        Municipio bogota = new Municipio();
        bogota.setNombre("Bogotá");
        bogota.setCodigo("11001"); 
        municipioRepository.save(bogota);

        Municipio soacha = new Municipio();
        soacha.setNombre("Soacha");
        soacha.setCodigo("25754");
        municipioRepository.save(soacha);

        Municipio zipaquira = new Municipio();
        zipaquira.setNombre("Zipaquirá");
        zipaquira.setCodigo("25899");
        municipioRepository.save(zipaquira);

 
        Departamento antioquia = new Departamento();
        antioquia.setNombre("Antioquia");
        departamentoRepository.save(antioquia);

        Municipio medellin = new Municipio();
        medellin.setNombre("Medellín");
        medellin.setCodigo("05001");
        municipioRepository.save(medellin);

        Municipio bello = new Municipio();
        bello.setNombre("Bello");
        bello.setCodigo("05088");
        municipioRepository.save(bello);

        Municipio envigado = new Municipio();
        envigado.setNombre("Envigado");
        envigado.setCodigo("05266");
        municipioRepository.save(envigado);

        Departamento valle = new Departamento();
        valle.setNombre("Valle del Cauca");
        departamentoRepository.save(valle);

        Municipio cali = new Municipio();
        cali.setNombre("Cali");
        cali.setCodigo("76001");
        municipioRepository.save(cali);

        Municipio palmira = new Municipio();
        palmira.setNombre("Palmira");
        palmira.setCodigo("76520");
        municipioRepository.save(palmira);

        Municipio buenaventura = new Municipio();
        buenaventura.setNombre("Buenaventura");
        buenaventura.setCodigo("76109");
        municipioRepository.save(buenaventura);

        System.out.println("Datos iniciales insertados: 3 departamentos y municipios con código DIAN.");
    }
}
