package com.github.nsorin.njobrest.web.controller;

import javax.validation.Valid;

import com.github.nsorin.njobrest.exception.EntityNotFoundException;
import com.github.nsorin.njobrest.model.Application;
import com.github.nsorin.njobrest.repository.ApplicationRepository;

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
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository repository;

    @GetMapping("")
    public Iterable<Application> fetch() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Application get(@PathVariable Integer id) throws EntityNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody @Valid Application application) {
        return this.repository.save(application);
    }

    @PutMapping("/{id}")
    public Application update(@PathVariable Integer id, @RequestBody @Valid Application newApplication)
            throws EntityNotFoundException {
        this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        newApplication.setId(id);
        return this.repository.save(newApplication);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws EntityNotFoundException {
        Application application = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        this.repository.delete(application);
    }

}