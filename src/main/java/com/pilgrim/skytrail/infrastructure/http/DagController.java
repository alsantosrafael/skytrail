package com.pilgrim.skytrail.infrastructure.http;

import com.pilgrim.skytrail.application.services.DagService;
import com.pilgrim.skytrail.domain.dtos.CreateDagRequest;
import com.pilgrim.skytrail.domain.dtos.FetchDagResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dags")
public class DagController {

	@Autowired
	private DagService dagService;

	@GetMapping()
	public List<FetchDagResponse> list() {
		return dagService.findAll();
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public FetchDagResponse create(@Valid @RequestBody CreateDagRequest request) {
		return dagService.create(request);
	}

	@PostMapping("/{dagId}/run")
	public void execute(@PathVariable UUID dagId) {
		System.out.println("Running: " + dagId);
	}
}
