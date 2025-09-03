package com.dsm.pdf_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsm.pdf_service.model.Template;


@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {

    Optional<Template> findByTemplateName(String templateName);
    boolean existsByTemplateName(String templateName);
}
