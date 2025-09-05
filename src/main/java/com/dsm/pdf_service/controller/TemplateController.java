package com.dsm.pdf_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsm.pdf_service.dto.TemplateRequest;
import com.dsm.pdf_service.model.Template;
import com.dsm.pdf_service.service.TemplateService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/templates")
public class TemplateController {
    
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<Template> create(@Valid @RequestBody TemplateRequest templateRequest) {
        Template template = new Template();
        template.setTemplateName(templateRequest.getTemplateName());
        template.setTemplateContent(templateRequest.getTemplateContent());
        template.setJsonSchema(templateRequest.getJsonSchema());
        template.setDescription(templateRequest.getDescription());
        
        Template createdTemplate = templateService.createTemplate(template);
        return ResponseEntity.ok(createdTemplate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplate(@PathVariable String id) {
        return templateService.getTemplateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Template>> getAllTemplates() {
        List<Template> templates = templateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable String id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/generate")
    public ResponseEntity<Void> generatePdf(@PathVariable String id, @RequestBody Map<String, Object> jsonData) {
        templateService.generatePdf(id, jsonData);
        return ResponseEntity.ok().build();
    }
}
