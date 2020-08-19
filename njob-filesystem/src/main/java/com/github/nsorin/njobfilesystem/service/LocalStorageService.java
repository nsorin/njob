package com.github.nsorin.njobfilesystem.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.github.nsorin.njobfilesystem.exception.FileNotFoundException;
import com.github.nsorin.njobfilesystem.exception.InvalidFileException;
import com.github.nsorin.njobfilesystem.exception.StorageException;
import com.github.nsorin.njobfilesystem.properties.StorageProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public LocalStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location at " + this.rootLocation, e);
        }
    }

    @Override
    public String store(MultipartFile file) throws InvalidFileException, StorageException {
        String fileKey = UUID.randomUUID().toString();
        if (file.isEmpty()) {
            throw new InvalidFileException();
        }

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, this.rootLocation.resolve(fileKey), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }

        return fileKey;
    }

    @Override
    public Resource load(String fileKey) throws FileNotFoundException {
        try {
            Path file = this.rootLocation.resolve(fileKey);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void delete(String fileKey) throws FileNotFoundException {
        try {
            Path file = this.rootLocation.resolve(fileKey);
            Files.delete(file);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

}