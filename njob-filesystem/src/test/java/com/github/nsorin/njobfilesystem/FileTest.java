package com.github.nsorin.njobfilesystem;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class FileTest {
    
    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    public void testUploadFile() throws IOException, Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "dummy".getBytes());

        this.mvc.perform(multipart("/").file(file)).andExpect(status().isCreated());
    }

    @Test
    public void testUploadEmptyFileThrowsException() throws IOException, Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "".getBytes());

        this.mvc.perform(multipart("/").file(file)).andExpect(status().isBadRequest());
    }
}