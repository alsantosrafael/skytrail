package com.pilgrim.skytrail.domain.mappers;

import com.pilgrim.skytrail.domain.dtos.FetchDagResponse;
import com.pilgrim.skytrail.domain.model.Dag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DagMapper {

    DagMapper INSTANCE = Mappers.getMapper(DagMapper.class);

    FetchDagResponse toFetchDagResponse(Dag dag);

    List<FetchDagResponse> toFetchDagResponseList(List<Dag> dags);
}