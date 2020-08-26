package com.github.nsorin.njobpdf.service;

import java.io.ByteArrayOutputStream;

import com.github.nsorin.njobpdf.document.DocumentContent;
import com.github.nsorin.njobpdf.document.Document.DocumentType;
import com.github.nsorin.njobpdf.exception.ExportException;
import com.github.nsorin.njobpdf.exception.InvalidDocumentTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class HTMLToPDFExportService implements PDFExportService {

    @Autowired
    private TemplateEngine templateEngine;

    public void init() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine.setTemplateResolver(templateResolver);
    }

    public ByteArrayOutputStream getPDF(DocumentType type, DocumentContent content) throws InvalidDocumentTypeException, ExportException {
        String template = this.resolveTemplate(type);
        String html = this.parseTemplate(template, content);
        
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(stream);
            stream.close();

            return stream;
        } catch (Exception e) {
            throw new ExportException();
        }
    }

    /**
     * Parse the template and load the content in it.
     * @param template
     * @param content
     * @return
     */
    protected String parseTemplate(String template, DocumentContent content) {
        Context context = new Context();
        context.setVariable("title", content.getTitle());
        context.setVariable("content", content.getContent());

        return this.templateEngine.process(template, context);
    }

    /**
     * Resolve the path to the template based on the document type.
     * @param type
     * @return
     * @throws InvalidDocumentTypeException
     */
    protected String resolveTemplate(DocumentType type) throws InvalidDocumentTypeException {
        String template = "";

        switch (type) {
            case SIMPLE:
                template = "simple";
                break;
            default:
                break;
        }

        if (template.isEmpty()) {
            throw new InvalidDocumentTypeException();
        }

        return template;
    }
}