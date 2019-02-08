package com.zeroexception.sqlconnection.serviceLayer.service;

import com.zeroexception.sqlconnection.mysqlmodel.Animal;
import java.util.List;

public interface AnimalService {

  List<Animal> findAllPage(int pageNumber, int size);

}
