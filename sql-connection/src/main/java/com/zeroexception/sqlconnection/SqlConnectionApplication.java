package com.zeroexception.sqlconnection;

import com.zeroexception.sqlconnection.postgresmodel.Person;
import com.zeroexception.sqlconnection.serviceLayer.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlConnectionApplication implements CommandLineRunner {

  @Autowired
  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(SqlConnectionApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Person person = new Person().setName("John");
    this.personRepository.save(person);
  }
}
