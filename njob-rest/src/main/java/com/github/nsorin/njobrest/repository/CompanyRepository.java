package com.github.nsorin.njobrest.repository;

import com.github.nsorin.njobrest.model.Company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    
}