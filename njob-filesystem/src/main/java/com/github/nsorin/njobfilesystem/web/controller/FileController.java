package com.github.nsorin.njobfilesystem.web.controller;

import com.github.nsorin.njobfilesystem.exception.FileNotFoundException;
import com.github.nsorin.njobfilesystem.exception.InvalidFileException;
import com.github.nsorin.njobfilesystem.response.FileResponse;
import com.github.nsorin.njobfilesystem.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@Controller
public class FileController {

    @Autowired
    StorageService storage;

    @GetMapping("/{fileKey}")
    @ResponseBody
    public ResponseEntity<Resource> download(@PathVariable String fileKey) throws FileNotFoundException {

        Resource resource = this.storage.load(fileKey);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FileResponse upload(@RequestParam("file") MultipartFile file) throws InvalidFileException {
        String fileKey = this.storage.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(fileKey).toUriString();

        return new FileResponse(fileKey, uri, file.getContentType(), file.getSize());
    }

    @DeleteMapping("/{fileKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String fileKey) throws FileNotFoundException {
        this.storage.delete(fileKey);
    }
}