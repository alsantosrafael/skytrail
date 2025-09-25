package com.pilgrim.skytrail.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateTasksBulkRequest {

    @Valid
    @NotEmpty(message = "Tasks list cannot be empty")
    private List<CreateTaskRequest> tasks;

    public CreateTasksBulkRequest() {}

    public CreateTasksBulkRequest(List<CreateTaskRequest> tasks) {
        this.tasks = tasks;
    }

    public List<CreateTaskRequest> getTasks() {
        return tasks;
    }

    public void setTasks(List<CreateTaskRequest> tasks) {
        this.tasks = tasks;
    }
}