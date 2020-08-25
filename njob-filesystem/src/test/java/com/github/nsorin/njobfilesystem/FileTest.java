package com.github.nsorin.njobfilesystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nsorin.njobfilesystem.exception.InvalidFileException;
import com.github.nsorin.njobfilesystem.exception.StorageException;
import com.github.nsorin.njobfilesystem.service.StorageService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, properties = "storage.location=./test-storage")
public class FileTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected StorageService storage;

    @AfterAll
    public void tearDown() throws IOException {
        this.storage.clear();
    }

    @Test
    public void testUploadFile() throws IOException, Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "dummy".getBytes());

        this.mvc.perform(multipart("/").file(file)).andExpect(status().isCreated());
    }

    @Test
    public void testUploadEmptyFileReturnsBadRequest() throws IOException, Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "".getBytes());

        this.mvc.perform(multipart("/").file(file)).andExpect(status().isBadRequest());
    }

    @Test
    public void testDownloadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "dummy".getBytes());
        String uuid = this.storage.store(file);
        this.mvc.perform(get("/" + uuid)).andExpect(status().isOk());
        this.storage.delete(uuid);
    }

    @Test
    public void testDownloadNonExistentFileReturnsNotFound() throws Exception {
        String uuid = UUID.randomUUID().toString();
        this.mvc.perform(get("/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "dummy".getBytes());
        String uuid = this.storage.store(file);
        this.mvc.perform(delete("/" + uuid)).andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteNonExistentFileReturnsNotFound() throws Exception {
        String uuid = UUID.randomUUID().toString();
        this.mvc.perform(delete("/" + uuid)).andExpect(status().isNotFound());
    }
}