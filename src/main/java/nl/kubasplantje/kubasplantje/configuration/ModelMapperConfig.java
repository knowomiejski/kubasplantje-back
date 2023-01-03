package nl.kubasplantje.kubasplantje.configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.kubasplantje.kubasplantje.dtos.ContactInfoDto;
import nl.kubasplantje.kubasplantje.dtos.FunFactDto;
import nl.kubasplantje.kubasplantje.dtos.ProjectDto;
import nl.kubasplantje.kubasplantje.dtos.TechDto;
import nl.kubasplantje.kubasplantje.models.ContactInfoModel;
import nl.kubasplantje.kubasplantje.models.FunFactModel;
import nl.kubasplantje.kubasplantje.models.ProjectModel;
import nl.kubasplantje.kubasplantje.models.TechModel;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //Tech
        modelMapper.createTypeMap(TechModel.class, TechDto.class);

        Converter<Set<ProjectModel>, List<Long>> projectListConverter = ctx -> {
            if(ctx.getSource() == null) return null;
            return ctx.getSource().stream().map(
                projectModel -> {
                    return projectModel.getId();
                }
            )
            .collect(Collectors.toList());
        };
        modelMapper.getTypeMap(TechModel.class, TechDto.class).addMappings(mapper -> mapper.using(projectListConverter).map(TechModel::getUsedInProjects, TechDto::setUsedInProjectIds));
        return modelMapper;
    }
}
