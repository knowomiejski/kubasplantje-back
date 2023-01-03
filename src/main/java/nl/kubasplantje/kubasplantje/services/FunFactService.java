package nl.kubasplantje.kubasplantje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.kubasplantje.kubasplantje.models.FunFactModel;
import nl.kubasplantje.kubasplantje.repositories.FunFactRepository;

@Service
public class FunFactService {

    private FunFactRepository funFactRepository;

    @Autowired
    public FunFactService(FunFactRepository funFactRepository) {
        this.funFactRepository = funFactRepository;
    }

    public List<FunFactModel> getAllFunFacts() {
        return this.funFactRepository.findAll();
    }

    public FunFactModel addFunFact(FunFactModel funFactModel) {
        return this.funFactRepository.save(funFactModel);
    }
}
