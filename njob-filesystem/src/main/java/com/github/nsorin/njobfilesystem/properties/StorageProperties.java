package com.github.nsorin.njobfilesystem.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    @Getter
    @Setter
    private String location;

}