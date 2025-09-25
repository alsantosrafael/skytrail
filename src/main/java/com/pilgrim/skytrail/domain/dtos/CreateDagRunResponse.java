package com.pilgrim.skytrail.domain.dtos;

import java.util.UUID;

public class CreateDagRunResponse {
	private UUID dagRunId;

	public UUID getDagRunId() {
		return dagRunId;
	}

	public void setDagRunId(UUID dagRunId) {
		this.dagRunId = dagRunId;
	}
}
