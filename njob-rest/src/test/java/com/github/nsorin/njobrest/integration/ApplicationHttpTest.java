package com.github.nsorin.njobrest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.nsorin.njobrest.model.Application;
import com.github.nsorin.njobrest.repository.ApplicationRepository;

public class ApplicationHttpTest extends HttpTest {

    @Autowired
    private ApplicationRepository repository;

    @Test
    public void testFetchApplications() throws Exception {
        this.mvc.perform(get("/applications").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetExistingApplication() throws Exception {
        Application application = this.repository.save(this.createTestApplication());
        this.mvc.perform(get("/applications/" + application.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingApplication() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(get("/applications/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateApplicationValid() throws Exception {
        Application application = this.createTestApplication();
        this.mvc.perform(post("/applications").content(this.mapper.writeValueAsString(application))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateApplicationInvalid() throws Exception {
        Application application = this.createTestApplication();
        application.setPriority(8);
        this.mvc.perform(post("/applications").content(this.mapper.writeValueAsString(application))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateExistingApplicationValid() throws Exception {
        Application application = this.repository.save(this.createTestApplication());
        application.setName("new name");
        this.mvc.perform(put("/applications/" + application.getId())
                .content(this.mapper.writeValueAsString(application)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateExistingApplicationInvalid() throws Exception {
        Application application = this.repository.save(this.createTestApplication());
        application.setPriority(10);
        this.mvc.perform(put("/applications/" + application.getId())
                .content(this.mapper.writeValueAsString(application)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateNonExistingApplicationValid() throws Exception {
        this.repository.deleteAll();
        Application application = this.createTestApplication();
        this.mvc.perform(put("/applications/1").content(this.mapper.writeValueAsString(application))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteExistingApplication() throws Exception {
        Application application = this.repository.save(this.createTestApplication());
        this.mvc.perform(delete("/applications/" + application.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertTrue(this.repository.findById(application.getId()).isEmpty());
    }

    @Test
    public void testDeleteNonExistingApplication() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(delete("/applications/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Create a dummy Application for testing purposes
     * 
     * @return
     */
    private Application createTestApplication() {
        Application application = new Application();
        application.setName("Test");
        application.setDescription("Test description");
        application.setPriority(3);
        return application;
    }

}