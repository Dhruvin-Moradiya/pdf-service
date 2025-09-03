package com.dsm.pdf_service.model;

import jakarta.persistence.*;
// import java.time.Instant;

@Entity
@Table(name = "templates")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "template_id", nullable = false, updatable = false)
    private String templateId;

    @Column(name = "template_name", nullable = false, unique = true)
    private String templateName;

    @Column(name = "template_content", nullable = false)
    private String templateContent;

    @Column(name = "json_schema", columnDefinition = "jsonb")
    private String jsonSchema;

    @Column(name = "description")
    private String description;

    // @Column(name = "created_at", nullable = false, updatable = false)
    // private Long createdAt = Instant.now().toEpochMilli();

    // @Column(name = "updated_at")
    // private Long updatedAt;

    // Getters and Setters
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getJsonSchema() {
        return jsonSchema;
    }

    public void setJsonSchema(String jsonSchema) {
        this.jsonSchema = jsonSchema;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public Long getCreatedAt() {
    //     return createdAt;
    // }

    // public void setCreatedAt(Long createdAt) {
    //     this.createdAt = createdAt;
    // }

    // public Long getUpdatedAt() {
    //     return updatedAt;
    // }

    // public void setUpdatedAt(Long updatedAt) {
    //     this.updatedAt = updatedAt;
    // }
}
