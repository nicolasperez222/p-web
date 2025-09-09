package com.proyecto.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.repository.CompanyRepository;


@Component
public class DbInitializer implements CommandLineRunner {

   

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        Person person = personRepository.save(
                new Person("123",
                        "Alice",
                        "Alisson"));
        Company company = companyRepository.save(
            new Company("company1", "123445")
        );

        company.getEmployees().add(person);
        // person.setEmployer(company);
        person.getEmployers().add(company);
        personRepository.save(person);

        
    
    }

}
