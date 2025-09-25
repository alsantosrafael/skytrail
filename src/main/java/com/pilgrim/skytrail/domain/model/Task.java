package com.pilgrim.skytrail.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dag_id", nullable = false)
    private Dag dag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "command_id")
    private Command command;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskRun> taskRuns;

    @OneToMany(mappedBy = "priorTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskDependency> nextDependencies;

    @OneToMany(mappedBy = "nextTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskDependency> priorDependencies;

    public Task() {}

    public Task(Dag dag, String name, String description) {
        this.dag = dag;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Dag getDag() {
        return dag;
    }

    public void setDag(Dag dag) {
        this.dag = dag;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TaskRun> getTaskRuns() {
        return taskRuns;
    }

    public void setTaskRuns(List<TaskRun> taskRuns) {
        this.taskRuns = taskRuns;
    }

    public List<TaskDependency> getNextDependencies() {
        return nextDependencies;
    }

    public void setNextDependencies(List<TaskDependency> nextDependencies) {
        this.nextDependencies = nextDependencies;
    }

    public List<TaskDependency> getPriorDependencies() {
        return priorDependencies;
    }

    public void setPriorDependencies(List<TaskDependency> priorDependencies) {
        this.priorDependencies = priorDependencies;
    }
}