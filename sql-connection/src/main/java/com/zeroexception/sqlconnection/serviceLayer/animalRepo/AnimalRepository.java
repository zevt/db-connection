package com.zeroexception.sqlconnection.serviceLayer.animalRepo;


import com.zeroexception.sqlconnection.mysqlmodel.Animal;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AnimalRepository extends JpaRepository<Animal, Long> {

  @Query("select a from Animal a")
  List<Animal> getAll();

  @NotNull
  Page<Animal> findAll(Pageable pageable);

  List<Animal> findAll(Sort sort);

  long countByName(String name);
}
