package com.zeroexception.dbconnection.serviceLayer.animalRepo;


import com.zeroexception.dbconnection.dataModel2.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
