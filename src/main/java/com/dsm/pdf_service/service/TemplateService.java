package com.dsm.pdf_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dsm.pdf_service.model.Template;
import com.dsm.pdf_service.repo.TemplateRepository;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
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
}
