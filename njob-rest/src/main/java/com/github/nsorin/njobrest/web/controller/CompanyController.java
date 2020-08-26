package com.github.nsorin.njobrest.web.controller;

import javax.validation.Valid;

import com.github.nsorin.njobrest.exception.EntityNotFoundException;
import com.github.nsorin.njobrest.model.Company;
import com.github.nsorin.njobrest.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @GetMapping("")
    public Iterable<Company> fetch() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Company get(@PathVariable Integer id) throws EntityNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody @Valid Company company) {
        return this.repository.save(company);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Integer id, @RequestBody @Valid Company newCompany)
            throws EntityNotFoundException {
        this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        newCompany.setId(id);
        return this.repository.save(newCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws EntityNotFoundException {
        Company company = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        this.repository.delete(company);
    }

}