package com.github.nsorin.njobpdf.service;

import java.io.ByteArrayOutputStream;

import com.github.nsorin.njobpdf.document.DocumentContent;
import com.github.nsorin.njobpdf.document.Document.DocumentType;
import com.github.nsorin.njobpdf.exception.ExportException;
import com.github.nsorin.njobpdf.exception.InvalidDocumentTypeException;;

public interface PDFExportService {

    public void init();

    public ByteArrayOutputStream getPDF(DocumentType type, DocumentContent content) throws InvalidDocumentTypeException, ExportException;
}