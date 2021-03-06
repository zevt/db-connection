package com.zeroexception.sqlconnection.controller;


import com.zeroexception.sqlconnection.mysqlmodel.Animal;
import com.zeroexception.sqlconnection.serviceLayer.animalRepo.AnimalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sql")
public class SqlQueryController {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private AnimalRepository animalRepo;

  @GetMapping("/all")
  public ResponseEntity<?> getAll() {

    List<String> nameList = jdbcTemplate
        .queryForList("SELECT name FROM experiment.tb_animal", String.class);

    for (String s : nameList) {
      System.out.println(s);
    }

    System.out.println(String.class.toString());

    List<Animal> animalList = this.animalRepo.getAll();

    return new ResponseEntity<>(animalList, HttpStatus.OK);
  }
}
