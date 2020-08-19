package com.github.nsorin.njobfilesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends Exception {

    private static final long serialVersionUID = -1699399714466895849L;

}