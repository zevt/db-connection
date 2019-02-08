package com.zeroexception.sqlconnection.serviceLayer.service;


import com.zeroexception.sqlconnection.mysqlmodel.Animal;
import com.zeroexception.sqlconnection.serviceLayer.animalRepo.AnimalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
