package com.github.nsorin.njobrest.web.controller;

import javax.validation.Valid;

import com.github.nsorin.njobrest.exception.EntityNotFoundException;
import com.github.nsorin.njobrest.model.Document;
import com.github.nsorin.njobrest.repository.DocumentRepository;

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
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository repository;

    @GetMapping("")
    public Iterable<Document> fetch() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Document get(@PathVariable Integer id) throws EntityNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Document create(@RequestBody @Valid Document document) {
        return this.repository.save(document);
    }

    @PutMapping("/{id}")
    public Document update(@PathVariable Integer id, @RequestBody @Valid Document newDocument)
            throws EntityNotFoundException {
        Document document = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        document.setName(newDocument.getName());
        document.setFileName(newDocument.getFileName());
        document.setFileType(newDocument.getFileType());
        document.setFileKey(newDocument.getFileKey());
        return this.repository.save(document);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws EntityNotFoundException {
        Document document = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        this.repository.delete(document);
    }

}