package com.zeroexception.sqlconnection.serviceLayer.repository;

import com.zeroexception.sqlconnection.postgresmodel.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
