package com.zeroexception.dbconnection.serviceLayer.service;

import com.zeroexception.dbconnection.dataModel2.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> findAllPage(int pageNumber, int size);
}
