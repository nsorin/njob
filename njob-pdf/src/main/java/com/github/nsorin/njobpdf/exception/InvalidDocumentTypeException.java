package com.github.nsorin.njobpdf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDocumentTypeException extends Exception {

    private static final long serialVersionUID = -8902251087269763966L;    
    
}