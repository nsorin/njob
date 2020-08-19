package com.github.nsorin.njobrest.web.controller;

import javax.validation.Valid;

import com.github.nsorin.njobrest.exception.EntityNotFoundException;
import com.github.nsorin.njobrest.model.Recruiter;
import com.github.nsorin.njobrest.repository.RecruiterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterRepository repository;

    @GetMapping("")
    public Iterable<Recruiter> fetch() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Recruiter get(@PathVariable Integer id) throws EntityNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Recruiter create(@RequestBody @Valid Recruiter recruiter) {
        return this.repository.save(recruiter);
    }

    @PutMapping("/{id}")
    public Recruiter update(@PathVariable Integer id, @RequestBody @Valid Recruiter newRecruiter)
            throws EntityNotFoundException {
        Recruiter recruiter = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        recruiter.setFirstName(newRecruiter.getFirstName());
        recruiter.setLastName(newRecruiter.getLastName());
        recruiter.setEmail(newRecruiter.getEmail());
        recruiter.setPhone(newRecruiter.getPhone());
        return this.repository.save(recruiter);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws EntityNotFoundException {
        Recruiter recruiter = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        this.repository.delete(recruiter);
    }

}