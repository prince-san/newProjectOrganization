package ru.nsu.mrprince;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.mrprince.model.entities.employees.Constructor;
import ru.nsu.mrprince.model.entities.employees.Engineer;
import ru.nsu.mrprince.repositories.EmployeeRepository;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            Constructor constructor = new Constructor();
            constructor.setPatents(1);
            constructor.setName("Гешка");
            constructor.setBirthDate(LocalDate.of(1970, 1, 1));
            repository.save(constructor);
            Engineer engineer = new Engineer();
            engineer.setName("Витька");
            engineer.setBirthDate(LocalDate.of(1990, 1, 1));
            repository.save(engineer);
        };
    }

}
