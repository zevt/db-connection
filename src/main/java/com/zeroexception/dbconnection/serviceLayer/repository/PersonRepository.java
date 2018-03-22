package com.zeroexception.dbconnection.serviceLayer.repository;

import com.zeroexception.dbconnection.dataModel.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
}
