package com.pilgrim.skytrail.infrastructure.repository;

import com.pilgrim.skytrail.domain.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommandRepository extends JpaRepository<Command, UUID> {
}