package com.github.nsorin.njobrest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.nsorin.njobrest.model.Company;
import com.github.nsorin.njobrest.repository.CompanyRepository;

public class CompanyHttpTest extends HttpTest {

    @Autowired
    private CompanyRepository repository;

    @Test
    public void testFetchCompanies() throws Exception {
        this.mvc.perform(get("/companies").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetExistingCompany() throws Exception {
        Company company = this.repository.save(this.createTestCompany());
        this.mvc.perform(get("/companies/" + company.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingCompany() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(get("/companies/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testCreateCompanyValid() throws Exception {
        Company company = this.createTestCompany();
        this.mvc.perform(post("/companies").content(this.mapper.writeValueAsString(company))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateCompanyInvalid() throws Exception {
        Company company = this.createTestCompany();
        company.setName(null);
        this.mvc.perform(post("/companies").content(this.mapper.writeValueAsString(company))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateExistingCompanyValid() throws Exception {
        Company company = this.repository.save(this.createTestCompany());
        company.setName("new name");
        this.mvc.perform(put("/companies/" + company.getId()).content(this.mapper.writeValueAsString(company))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateExistingCompanyInvalid() throws Exception {
        Company company = this.repository.save(this.createTestCompany());
        company.setName(null);
        this.mvc.perform(put("/companies/" + company.getId()).content(this.mapper.writeValueAsString(company))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateNonExistingCompanyValid() throws Exception {
        this.repository.deleteAll();
        Company company = this.createTestCompany();
        this.mvc.perform(put("/companies/1").content(this.mapper.writeValueAsString(company))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteExistingCompany() throws Exception {
        Company company = this.repository.save(this.createTestCompany());
        this.mvc.perform(delete("/companies/" + company.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertTrue(this.repository.findById(company.getId()).isEmpty());
    }

    @Test
    public void testDeleteNonExistingCompany() throws Exception {
        this.repository.deleteAll();
        this.mvc.perform(delete("/companies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Create a dummy Company for testing purposes
     * 
     * @return
     */
    private Company createTestCompany() {
        Company company = new Company();
        company.setName("Test");
        company.setAddress("10 Example Street");
        company.setCity("City");
        company.setZipCode(99999);
        return company;
    }

}