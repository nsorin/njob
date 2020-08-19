package com.github.nsorin.njobrest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Application {

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
    private String description;

    @Getter
    @Setter
    @NotNull
    @Min(1)
    @Max(5)
    private Integer priority;

    @ManyToOne
    @Getter
    @Setter
    private Company company;

    @OneToMany(mappedBy = "application")
    @Getter
    @Setter
    private List<Document> documents;
}