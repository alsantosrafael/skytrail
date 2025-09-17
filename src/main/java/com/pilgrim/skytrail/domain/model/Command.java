package com.pilgrim.skytrail.domain.model;

import com.pilgrim.skytrail.domain.enums.CommandType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Command {

	@Id
	@GeneratedValue
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(length = 100)
	private CommandType type;

	@Column(name = "protocol", 	length = 1000)
	private String protocol;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;







}
