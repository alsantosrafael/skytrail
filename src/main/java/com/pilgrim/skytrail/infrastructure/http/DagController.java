package com.pilgrim.skytrail.infrastructure.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dags")
public class DagController {

	@PostMapping("/{dagId}/run")
	public void execute(@PathVariable UUID dagId) {
		System.out.println("Running: " + dagId);
	}
}
