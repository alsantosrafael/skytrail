package com.pilgrim.skytrail.infrastructure.repository;

import com.pilgrim.skytrail.domain.model.TaskRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRunRepository extends JpaRepository<TaskRun, UUID> {
}