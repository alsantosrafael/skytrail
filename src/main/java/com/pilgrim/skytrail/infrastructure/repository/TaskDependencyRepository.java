package com.pilgrim.skytrail.infrastructure.repository;

import com.pilgrim.skytrail.domain.model.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskDependencyRepository extends JpaRepository<TaskDependency, UUID> {
}