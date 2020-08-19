package com.github.nsorin.njobrest.repository;

import com.github.nsorin.njobrest.model.Application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
    
}