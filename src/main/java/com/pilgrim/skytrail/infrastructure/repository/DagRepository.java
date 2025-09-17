package com.pilgrim.skytrail.infrastructure.repository;

import com.pilgrim.skytrail.domain.model.Dag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DagRepository extends JpaRepository<Dag, UUID> {
}
