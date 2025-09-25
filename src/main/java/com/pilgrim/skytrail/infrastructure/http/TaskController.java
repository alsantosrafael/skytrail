package com.pilgrim.skytrail.infrastructure.http;

import com.pilgrim.skytrail.application.services.TaskService;
import com.pilgrim.skytrail.domain.dtos.CreateTasksBulkRequest;
import com.pilgrim.skytrail.domain.dtos.FetchTaskResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dags")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{dagId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FetchTaskResponse> createTasks(
            @PathVariable UUID dagId,
            @Valid @RequestBody CreateTasksBulkRequest request) {
        return taskService.createTasksBulk(dagId, request);
    }

    @GetMapping("/{dagId}/tasks")
    public List<FetchTaskResponse> getTasks(@PathVariable UUID dagId) {
        return taskService.findTasksByDagId(dagId);
    }
}