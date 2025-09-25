package com.pilgrim.skytrail.application.services;

import com.pilgrim.skytrail.domain.dtos.CreateTaskRequest;
import com.pilgrim.skytrail.domain.dtos.CreateTasksBulkRequest;
import com.pilgrim.skytrail.domain.dtos.FetchTaskResponse;
import com.pilgrim.skytrail.domain.model.Command;
import com.pilgrim.skytrail.domain.model.Dag;
import com.pilgrim.skytrail.domain.model.Task;
import com.pilgrim.skytrail.domain.model.TaskDependency;
import com.pilgrim.skytrail.infrastructure.repository.CommandRepository;
import com.pilgrim.skytrail.infrastructure.repository.DagRepository;
import com.pilgrim.skytrail.infrastructure.repository.TaskDependencyRepository;
import com.pilgrim.skytrail.infrastructure.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private DagRepository dagRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private TaskDependencyRepository taskDependencyRepository;

    @Transactional
    public List<FetchTaskResponse> createTasksBulk(UUID dagId, CreateTasksBulkRequest request) {
        Dag dag = dagRepository.findById(dagId)
                .orElseThrow(() -> new IllegalArgumentException("DAG not found with id: " + dagId));

        List<Task> createdTasks = new ArrayList<>();
        List<FetchTaskResponse> responses = new ArrayList<>();

        for (CreateTaskRequest taskRequest : request.getTasks()) {
            Command command = new Command();
            command.setType(taskRequest.getCommandType());
            command.setProtocol(taskRequest.getProtocol());
            Command savedCommand = commandRepository.save(command);

            Task task = new Task();
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setDag(dag);
            task.setCommand(savedCommand);
            Task savedTask = taskRepository.save(task);

            createdTasks.add(savedTask);

            FetchTaskResponse response = new FetchTaskResponse(
                    savedTask.getId(),
                    savedTask.getName(),
                    savedTask.getDescription(),
                    dag.getId(),
                    savedCommand.getId(),
                    savedCommand.getType(),
                    savedCommand.getProtocol(),
                    savedTask.getCreatedAt(),
                    savedTask.getUpdatedAt()
            );
            responses.add(response);
        }

        for (int i = 1; i < createdTasks.size(); i++) {
            Task priorTask = createdTasks.get(i - 1);
            Task nextTask = createdTasks.get(i);

            TaskDependency dependency = new TaskDependency(priorTask, nextTask);
            taskDependencyRepository.save(dependency);
        }

        return responses;
    }

    public List<FetchTaskResponse> findTasksByDagId(UUID dagId) {
        List<Task> tasks = taskRepository.findByDagId(dagId);
        List<FetchTaskResponse> responses = new ArrayList<>();

        for (Task task : tasks) {
            Command command = task.getCommand();
            FetchTaskResponse response = new FetchTaskResponse(
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getDag().getId(),
                    command != null ? command.getId() : null,
                    command != null ? command.getType() : null,
                    command != null ? command.getProtocol() : null,
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
            responses.add(response);
        }

        return responses;
    }
}