package com.zeroexception.dbconnection.serviceLayer.animalRepo;


import com.zeroexception.dbconnection.dataModel2.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("select a from Animal a")
    List<Animal> getAll();
}
