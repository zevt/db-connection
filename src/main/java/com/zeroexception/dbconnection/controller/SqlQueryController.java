package com.zeroexception.dbconnection.controller;


import com.zeroexception.dbconnection.dataModel2.Animal;
import com.zeroexception.dbconnection.serviceLayer.animalRepo.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sql")
public class SqlQueryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AnimalRepository animalRepo;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {

        List<String> nameList = jdbcTemplate.queryForList("SELECT name FROM experiment.tb_animal", String.class);

        for (String s : nameList) {
            System.out.println(s);
        }

        List<Animal> animalList = this.animalRepo.getAll();

        return new ResponseEntity<>(animalList, HttpStatus.OK);
    }
}
