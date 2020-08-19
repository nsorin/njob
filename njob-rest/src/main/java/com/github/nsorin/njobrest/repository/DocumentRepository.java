package com.github.nsorin.njobrest.repository;

import com.github.nsorin.njobrest.model.Document;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {
    
}