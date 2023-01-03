package nl.kubasplantje.kubasplantje.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.kubasplantje.kubasplantje.dtos.FunFactDto;
import nl.kubasplantje.kubasplantje.models.FunFactModel;
import nl.kubasplantje.kubasplantje.services.FunFactService;

@RestController
@RequestMapping(path = "api/v1/funfact")
public class FunFactController {

    @Autowired
    private ModelMapper modelMapper;
    private FunFactService funFactService;

    @Autowired
    public FunFactController(ModelMapper modelMapper, FunFactService funFactService) {
        this.modelMapper = modelMapper;
        this.funFactService = funFactService;
    }

    @GetMapping
    public ResponseEntity<List<FunFactDto>> getAllFunFacts() {
        List<FunFactModel> funFactModels = this.funFactService.getAllFunFacts();
        List<FunFactDto> funFactDtos = new ArrayList<>();
        for (FunFactModel funFactModel : funFactModels) {
            funFactDtos.add(convertFunFactModelToFunFactDto(funFactModel));
        }
        return ResponseEntity.ok(funFactDtos);
    }

    @PostMapping
    public ResponseEntity<FunFactDto> addNewFunFact(@RequestBody FunFactDto funFactDto) {
        FunFactModel funFactModel = convertFunFactDtoToFunFactModel(funFactDto);
        FunFactDto addedFunFact = convertFunFactModelToFunFactDto(this.funFactService.addFunFact(funFactModel));
        return ResponseEntity.ok(addedFunFact);
    }

    private FunFactDto convertFunFactModelToFunFactDto(FunFactModel funFactModel) {
        return this.modelMapper.map(funFactModel, FunFactDto.class);
    }

    private FunFactModel convertFunFactDtoToFunFactModel(FunFactDto funFactDto) {
        return this.modelMapper.map(funFactDto, FunFactModel.class);
    }
}
