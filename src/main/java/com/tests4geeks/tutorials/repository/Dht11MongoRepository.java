package com.tests4geeks.tutorials.repository;

import com.tests4geeks.tutorials.model.Dht11Model;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Dht11MongoRepository extends MongoRepository<Dht11Model, String> {

    List<Dht11Model> findTop10ByOrderByTimeDesc();

}