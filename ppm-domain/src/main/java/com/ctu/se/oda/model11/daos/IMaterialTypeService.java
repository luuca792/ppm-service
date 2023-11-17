package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.materialType.RetrieveMaterialTypeQueryResponse;

public interface IMaterialTypeService {
	public void createMaterialType(CreateMaterialTypeCommandRequest request);
	public void deleteMaterialType(UUID materialId);
	public RetrieveMaterialTypeQueryResponse getMaterialTypeById(UUID materialTypeId);
	public List<RetrieveMaterialTypeQueryResponse> getAllMaterialTypes();
}
