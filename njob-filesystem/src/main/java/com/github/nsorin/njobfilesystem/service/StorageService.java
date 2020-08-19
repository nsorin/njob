package com.github.nsorin.njobfilesystem.service;

import com.github.nsorin.njobfilesystem.exception.FileNotFoundException;
import com.github.nsorin.njobfilesystem.exception.InvalidFileException;
import com.github.nsorin.njobfilesystem.exception.StorageException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public void init();

    public String store(MultipartFile file) throws InvalidFileException, StorageException;

    public Resource load(String fileKey) throws FileNotFoundException;

    public void delete(String fileKey) throws FileNotFoundException;
}