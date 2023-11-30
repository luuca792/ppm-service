package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.materialType.UpdateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.materialType.RetrieveMaterialTypeQueryResponse;
import jakarta.validation.Valid;

public interface IMaterialTypeService {
	public void createMaterialType(@Valid CreateMaterialTypeCommandRequest request);
	public void updateMaterialType(@Valid UpdateMaterialTypeCommandRequest request);
	public void deleteMaterialType(UUID materialId);
	public RetrieveMaterialTypeQueryResponse getMaterialTypeById(UUID materialTypeId);
	public List<RetrieveMaterialTypeQueryResponse> getAllMaterialTypes();
}
