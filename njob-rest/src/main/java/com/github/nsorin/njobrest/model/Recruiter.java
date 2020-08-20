package com.github.nsorin.njobrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.nsorin.njobrest.model.validation.ValidationPattern;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Recruiter {

    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Pattern(regexp = ValidationPattern.PERSON_NAME)
    private String firstName;

    @Getter
    @Setter
    @Pattern(regexp = ValidationPattern.PERSON_NAME)
    private String lastName;

    @Getter
    @Setter
    @Email
    private String email;

    @Getter
    @Setter
    @Pattern(regexp = ValidationPattern.PHONE_NUMBER)
    private String phone;

    @JsonBackReference
    @ManyToOne
    @Getter
    @Setter
    private Company company;

}