package com.pilgrim.skytrail.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateDagRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    private String scheduleExpr;

    public CreateDagRequest() {}

    public CreateDagRequest(String name, String description, String scheduleExpr) {
        this.name = name;
        this.description = description;
        this.scheduleExpr = scheduleExpr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScheduleExpr() {
        return scheduleExpr;
    }

    public void setScheduleExpr(String scheduleExpr) {
        this.scheduleExpr = scheduleExpr;
    }
}