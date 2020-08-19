package com.github.nsorin.njobfilesystem.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(HttpStatus.CREATED)
public class FileResponse {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String uri;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private long size;

    public FileResponse(String name, String uri, String type, long size) {
        this.name = name;
        this.uri = uri;
        this.type = type;
        this.size = size;
    }
}