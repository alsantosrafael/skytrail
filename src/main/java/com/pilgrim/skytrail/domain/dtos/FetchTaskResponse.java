package com.pilgrim.skytrail.domain.dtos;

import com.pilgrim.skytrail.domain.enums.CommandType;

import java.time.LocalDateTime;
import java.util.UUID;

public class FetchTaskResponse {

    private UUID id;
    private String name;
    private String description;
    private UUID dagId;
    private UUID commandId;
    private CommandType commandType;
    private String protocol;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FetchTaskResponse() {}

    public FetchTaskResponse(UUID id, String name, String description, UUID dagId,
                           UUID commandId, CommandType commandType, String protocol,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dagId = dagId;
        this.commandId = commandId;
        this.commandType = commandType;
        this.protocol = protocol;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getDagId() {
        return dagId;
    }

    public void setDagId(UUID dagId) {
        this.dagId = dagId;
    }

    public UUID getCommandId() {
        return commandId;
    }

    public void setCommandId(UUID commandId) {
        this.commandId = commandId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
}