package com.github.nsorin.njobrest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.nsorin.njobrest.model.Recruiter;
import com.github.nsorin.njobrest.repository.RecruiterRepository;

public class RecruiterHttpTest extends HttpTest {

    @Autowired
    private RecruiterRepository repository;

    @Test
    public void testFetchRecruiters() throws Exception {
        this.mvc.perform(get("/recruiters").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetExistingRecruiter() throws Exception {
        Recruiter recruiter = this.repository.save(this.createTestRecruiter());
        this.mvc.perform(get("/recruiters/" + recruiter.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingRecruiter() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(get("/recruiters/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testCreateRecruiterValid() throws Exception {
        Recruiter recruiter = this.createTestRecruiter();
        this.mvc.perform(post("/recruiters").content(this.mapper.writeValueAsString(recruiter))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateRecruiterInvalid() throws Exception {
        Recruiter recruiter = this.createTestRecruiter();
        recruiter.setEmail("invalid");
        this.mvc.perform(post("/recruiters").content(this.mapper.writeValueAsString(recruiter))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateExistingRecruiterValid() throws Exception {
        Recruiter recruiter = this.repository.save(this.createTestRecruiter());
        recruiter.setFirstName("newname");
        this.mvc.perform(put("/recruiters/" + recruiter.getId()).content(this.mapper.writeValueAsString(recruiter))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateExistingRecruiterInvalid() throws Exception {
        Recruiter recruiter = this.repository.save(this.createTestRecruiter());
        recruiter.setEmail("invalid");
        this.mvc.perform(put("/recruiters/" + recruiter.getId()).content(this.mapper.writeValueAsString(recruiter))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateNonExistingRecruiterValid() throws Exception {
        this.repository.deleteAll();
        Recruiter recruiter = this.createTestRecruiter();
        this.mvc.perform(put("/recruiters/1").content(this.mapper.writeValueAsString(recruiter))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteExistingRecruiter() throws Exception {
        Recruiter recruiter = this.repository.save(this.createTestRecruiter());
        this.mvc.perform(delete("/recruiters/" + recruiter.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertTrue(this.repository.findById(recruiter.getId()).isEmpty());
    }

    @Test
    public void testDeleteNonExistingRecruiter() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(delete("/recruiters/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Create a dummy Recruiter for testing purposes
     * 
     * @return
     */
    private Recruiter createTestRecruiter() {
        Recruiter recruiter = new Recruiter();
        recruiter.setFirstName("Test");
        recruiter.setLastName("Test");
        recruiter.setEmail("example@example.org");
        recruiter.setPhone("+00000000000");
        return recruiter;
    }

}