package com.ctu.se.oda.model11;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.materialType.UpdateMaterialTypeCommandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.IMaterialTypeService;
import com.ctu.se.oda.model11.interfaces.IMaterialTypeApplication;
import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.materialType.RetrieveMaterialTypeQueryResponse;

@Component
public class MaterialTypeApplication implements IMaterialTypeApplication{
	
	@Autowired
	private IMaterialTypeService materialTypeService;

	@Override
	public List<RetrieveMaterialTypeQueryResponse> getAllMaterialType() {
		return materialTypeService.getAllMaterialTypes();
	}

	@Override
	public RetrieveMaterialTypeQueryResponse getMaterialTypeById(UUID id) {
		return materialTypeService.getMaterialTypeById(id);
	}

	@Override
	public void createMaterial(CreateMaterialTypeCommandRequest request) {
		materialTypeService.createMaterialType(request);
	}

	@Override
	public void updateMaterialType(UpdateMaterialTypeCommandRequest request) {
		materialTypeService.updateMaterialType(request);
	}

	@Override
	public void deleteMaterialTypeById(UUID id) {
		materialTypeService.deleteMaterialType(id);
	}

}
