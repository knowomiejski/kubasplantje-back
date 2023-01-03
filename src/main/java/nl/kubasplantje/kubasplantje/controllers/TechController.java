package nl.kubasplantje.kubasplantje.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.kubasplantje.kubasplantje.dtos.TechDto;
import nl.kubasplantje.kubasplantje.models.ProjectModel;
import nl.kubasplantje.kubasplantje.models.TechModel;
import nl.kubasplantje.kubasplantje.services.TechService;

@RestController
@RequestMapping(path = "api/v1/tech")
public class TechController {

    @Autowired
    private ModelMapper modelMapper;
    private TechService techService;

    @Autowired
    public TechController(TechService techService) {
        this.techService = techService;
    }

    @GetMapping
    public List<TechDto> getAllTechs() {
        List<TechModel> techModels = this.techService.getTechs();
        List<TechDto> techDtos = new ArrayList<>();
        for (TechModel techModel : techModels) {
            techDtos.add(convertModelToDto(techModel));
        }
        return techDtos;
    }

    @PostMapping
    public ResponseEntity<String> addNewTech(@RequestBody TechDto techDto) {
        try {
            this.techService.addTech(convertDtoToModel(techDto));
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Sucess");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private TechModel convertDtoToModel(TechDto techDto) {
        return this.modelMapper.map(techDto, TechModel.class);
    }

    private TechDto convertModelToDto(TechModel techModel) {
        return this.modelMapper.map(techModel, TechDto.class);
    }
}
