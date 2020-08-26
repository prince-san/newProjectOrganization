package ru.nsu.mrprince.configs;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.mrprince.model.dtos.EfficiencyDTO;
import ru.nsu.mrprince.model.entities.Contract;
import ru.nsu.mrprince.model.entities.Department;
import ru.nsu.mrprince.model.entities.Project;
import ru.nsu.mrprince.model.entities.Work;
import ru.nsu.mrprince.model.entities.employees.Employee;
import ru.nsu.mrprince.model.entities.employees.Supervisor;
import ru.nsu.mrprince.model.entities.employees.Technician;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(new AbstractConverter<Department, String>() {
            protected String convert(Department department) {
                return department.getName();
            }
        });

        modelMapper.addConverter(new AbstractConverter<Supervisor, String>() {
            protected String convert(Supervisor supervisor) {
                return supervisor.getName();
            }
        });

        modelMapper.addConverter(new AbstractConverter<Contract, String>() {
            protected String convert(Contract contract) {
                return contract.getName();
            }
        });

        modelMapper.addConverter(new AbstractConverter<Project, String>() {
            protected String convert(Project project) {
                return project.getName();
            }
        });

        modelMapper.addConverter(new AbstractConverter<Technician, String>() {
            protected String convert(Technician technician) {
                return technician.getName();
            }
        });

        modelMapper.typeMap(Work.class, EfficiencyDTO.class).addMapping(Work::getDescription, EfficiencyDTO::setName);

        return modelMapper;
    }

}
