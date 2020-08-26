package com.github.nsorin.njobpdf.web.controller;

import com.github.nsorin.njobpdf.document.DocumentContent;
import com.github.nsorin.njobpdf.document.Document.DocumentType;
import com.github.nsorin.njobpdf.exception.ExportException;
import com.github.nsorin.njobpdf.exception.InvalidDocumentTypeException;
import com.github.nsorin.njobpdf.service.PDFExportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {
 
    @Autowired
    private PDFExportService export;

    @PostMapping("")
    public byte[] export(@RequestBody DocumentContent content) throws InvalidDocumentTypeException, ExportException {
        return this.export.getPDF(DocumentType.SIMPLE, content).toByteArray();
    }
}