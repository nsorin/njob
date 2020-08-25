package com.github.nsorin.njobfilesystem.service;

import com.github.nsorin.njobfilesystem.exception.FileNotFoundException;
import com.github.nsorin.njobfilesystem.exception.InvalidFileException;
import com.github.nsorin.njobfilesystem.exception.StorageException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    /**
     * Init the storage.
     */
    public void init();

    /**
     * Store a non-empty file in the storage.
     * @param file
     * @return
     * @throws InvalidFileException
     * @throws StorageException
     */
    public String store(MultipartFile file) throws InvalidFileException, StorageException;

    /**
     * Load a file in the storage if it exists.
     * @param fileKey
     * @return
     * @throws FileNotFoundException
     */
    public Resource load(String fileKey) throws FileNotFoundException;

    /**
     * Delete a file in the storage if it exists.
     * @param fileKey
     * @throws FileNotFoundException
     */
    public void delete(String fileKey) throws FileNotFoundException;

    /**
     * Clear the storage by deleting its entire content.
     */
    public void clear();
}