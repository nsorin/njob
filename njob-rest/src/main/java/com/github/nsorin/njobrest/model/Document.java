package com.github.nsorin.njobrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Document {

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
    @NotNull
    private String fileName;

    @Getter
    @Setter
    @NotNull
    private String fileType;

    @Getter
    @Setter
    @NotNull
    private String fileKey;

    @ManyToOne
    @Getter
    @Setter
    private Application application;
}