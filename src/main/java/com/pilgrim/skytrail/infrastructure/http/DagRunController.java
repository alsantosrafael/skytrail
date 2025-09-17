package com.pilgrim.skytrail.infrastructure.http;

import com.pilgrim.skytrail.application.services.DagService;
import com.pilgrim.skytrail.domain.dtos.FetchDagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dag-runs")
public class DagRunController {

	@Autowired
	private DagService dagService;

	@PostMapping("/{dagId}/start")
	public void execute(@PathVariable UUID dagId) {
		System.out.println("Running: " + dagId);
	}
}
