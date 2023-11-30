package com.ctu.se.oda.model11.interfaces;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.materialType.UpdateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.materialType.RetrieveMaterialTypeQueryResponse;

public interface IMaterialTypeApplication {
	public List<RetrieveMaterialTypeQueryResponse>getAllMaterialType();
	public RetrieveMaterialTypeQueryResponse getMaterialTypeById(UUID id);
	public void createMaterial(CreateMaterialTypeCommandRequest request);
	public void updateMaterialType(UpdateMaterialTypeCommandRequest request);
	public void deleteMaterialTypeById(UUID id);
}
