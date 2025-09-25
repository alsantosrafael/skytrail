package com.pilgrim.skytrail.domain.dtos;

import com.pilgrim.skytrail.domain.enums.CommandType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTaskRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull(message = "Command type is required")
    private CommandType commandType;

    @NotBlank(message = "Protocol (eTag) is required")
    @Size(max = 1000, message = "Protocol must not exceed 1000 characters")
    private String protocol;

    public CreateTaskRequest() {}

    public CreateTaskRequest(String name, String description, CommandType commandType, String protocol) {
        this.name = name;
        this.description = description;
        this.commandType = commandType;
        this.protocol = protocol;
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
}