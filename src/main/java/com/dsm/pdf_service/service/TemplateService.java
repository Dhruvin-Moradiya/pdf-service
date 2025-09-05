package com.dsm.pdf_service.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dsm.pdf_service.model.Template;
import com.dsm.pdf_service.repo.TemplateRepository;
import com.dsm.pdf_service.service.storage.FileStorageService;
import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final FileStorageService fileStorageService;

    public TemplateService(TemplateRepository templateRepository, FileStorageService fileStorageService) {
        this.templateRepository = templateRepository;
        this.fileStorageService = fileStorageService;
    }

    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    public Optional<Template> getTemplateById(String id) {
        return templateRepository.findById(id);
    }

    public Optional<Template> getTemplateByName(String name) {
        return templateRepository.findByTemplateName(name);
    }

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public void deleteTemplate(String id) {
        templateRepository.deleteById(id);
    }

    public String generatePdf(String templateId, Map<String, Object> jsonData) {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));

        String templateContent = template.getTemplateContent();
        Handlebars handlebars = new Handlebars();

        try {
            String populatedContent = handlebars.compileInline(templateContent).apply(jsonData);

            // Generate PDF into memory
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(populatedContent, pdfOutputStream);

            // Upload to S3
            ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfOutputStream.toByteArray());
            String s3Key = "templates/" + template.getTemplateName() + "-" + System.currentTimeMillis() + ".pdf";

            return fileStorageService.uploadFile(inputStream, s3Key);

        } catch (Exception e) {
            throw new RuntimeException("Error processing template", e);
        }
    }
}
