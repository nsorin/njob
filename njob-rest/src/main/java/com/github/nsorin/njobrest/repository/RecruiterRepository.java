package com.github.nsorin.njobrest.repository;

import com.github.nsorin.njobrest.model.Recruiter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends CrudRepository<Recruiter, Integer> {
    
}