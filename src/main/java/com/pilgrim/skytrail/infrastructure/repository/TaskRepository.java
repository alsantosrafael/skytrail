package com.pilgrim.skytrail.infrastructure.repository;

import com.pilgrim.skytrail.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByDagId(UUID dagId);
}