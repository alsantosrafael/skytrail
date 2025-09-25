package com.pilgrim.skytrail.domain.mappers;

import com.pilgrim.skytrail.domain.dtos.CreateDagRequest;
import com.pilgrim.skytrail.domain.dtos.FetchDagResponse;
import com.pilgrim.skytrail.domain.model.Dag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DagMapper {

    DagMapper INSTANCE = Mappers.getMapper(DagMapper.class);

    FetchDagResponse toFetchDagResponse(Dag dag);

    List<FetchDagResponse> toFetchDagResponseList(List<Dag> dags);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Dag toDag(CreateDagRequest request);
}