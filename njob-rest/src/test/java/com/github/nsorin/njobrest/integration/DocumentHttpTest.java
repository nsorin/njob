package com.github.nsorin.njobrest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.nsorin.njobrest.model.Document;
import com.github.nsorin.njobrest.repository.DocumentRepository;

public class DocumentHttpTest extends HttpTest {

    @Autowired
    private DocumentRepository repository;

    @Test
    public void testFetchDocuments() throws Exception {
        this.mvc.perform(get("/documents").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetExistingDocument() throws Exception {
        Document document = this.repository.save(this.createTestDocument());
        this.mvc.perform(get("/documents/" + document.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingDocument() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(get("/documents/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testCreateDocumentValid() throws Exception {
        Document document = this.createTestDocument();
        this.mvc.perform(post("/documents").content(this.mapper.writeValueAsString(document))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void testUpdateExistingDocumentValid() throws Exception {
        Document document = this.repository.save(this.createTestDocument());
        document.setName("new name");
        this.mvc.perform(put("/documents/" + document.getId()).content(this.mapper.writeValueAsString(document))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateNonExistingDocumentValid() throws Exception {
        this.repository.deleteAll();
        Document document = this.createTestDocument();
        this.mvc.perform(put("/documents/1").content(this.mapper.writeValueAsString(document))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteExistingDocument() throws Exception {
        Document document = this.repository.save(this.createTestDocument());
        this.mvc.perform(delete("/documents/" + document.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertTrue(this.repository.findById(document.getId()).isEmpty());
    }

    @Test
    public void testDeleteNonExistingDocument() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(delete("/documents/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Create a dummy Document for testing purposes
     * 
     * @return
     */
    private Document createTestDocument() {
        Document document = new Document();
        document.setName("Test");
        document.setFileName("Test.txt");
        document.setFileType("text/plain");
        document.setFileKey("f2837b45-a088-458e-ae4b-b9aaa76cb955");
        return document;
    }

}