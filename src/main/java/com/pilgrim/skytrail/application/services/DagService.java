package com.pilgrim.skytrail.application.services;

import com.pilgrim.skytrail.domain.dtos.FetchDagResponse;
import com.pilgrim.skytrail.domain.mappers.DagMapper;
import com.pilgrim.skytrail.infrastructure.repository.DagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DagService {

	@Autowired
	private DagRepository dagRepository;

	public List<FetchDagResponse> findAll() {
		return DagMapper.INSTANCE.toFetchDagResponseList(dagRepository.findAll());
	}
}
