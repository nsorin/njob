package com.github.nsorin.njobrest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Company {

    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotNull
    private String name;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private Integer zipCode;

    @Getter
    @Setter
    @URL
    private String webSite;

    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    @Getter
    @Setter
    private List<Application> applications;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    @Getter
    @Setter
    private List<Recruiter> recruiters;
}