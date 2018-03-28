package com.zeroexception.dbconnection.serviceLayer.service;


import com.zeroexception.dbconnection.dataModel2.Animal;
import com.zeroexception.dbconnection.serviceLayer.animalRepo.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalRepository animalRepo;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public List<Animal> findAllPage(int pageNumber, int size) {
        Page<Animal> page = this.animalRepo.findAll(PageRequest.of(pageNumber, size));
        return page.getContent();
    }
}
