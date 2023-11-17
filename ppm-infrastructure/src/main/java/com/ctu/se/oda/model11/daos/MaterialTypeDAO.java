package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctu.se.oda.model11.entities.MaterialType;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.materialType.RetrieveMaterialTypeQueryResponse;
import com.ctu.se.oda.model11.repositories.IMaterialTypeRepository;

import jakarta.validation.Valid;

@Service
public class MaterialTypeDAO implements IMaterialTypeService{
	
	@Autowired
	private IMaterialTypeRepository materialTypeRepository;
	
	@Autowired
	private IInfrastructureMapper<CreateMaterialTypeCommandRequest, MaterialType> mapper;
	
	@Override
	public void createMaterialType(@Valid CreateMaterialTypeCommandRequest request) {
		materialTypeRepository.save(mapper.convert(request));
	}

	@Override
	public void deleteMaterialType(UUID materialId) {
		Optional<MaterialType> materialType = materialTypeRepository.findById(materialId);
		if (!materialType.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_TYPE_ID_NOT_FOUND);
		}
		materialTypeRepository.deleteById(materialId);
	}

	@Override
	public RetrieveMaterialTypeQueryResponse getMaterialTypeById(UUID materialTypeId) {
		Optional<MaterialType> materialType = materialTypeRepository.findById(materialTypeId);
		if (!materialType.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_TYPE_ID_NOT_FOUND);
		}
		MaterialType retrievedMaterialType = materialType.get();
		return RetrieveMaterialTypeQueryResponse.builder()
				.id(retrievedMaterialType.getId())
				.materialTypeName(retrievedMaterialType.getName())
				.build();
	}

	@Override
	public List<RetrieveMaterialTypeQueryResponse> getAllMaterialTypes() {
		List<MaterialType> materialTypes = materialTypeRepository.findAll();
		return materialTypes.stream().map(item -> RetrieveMaterialTypeQueryResponse.builder()
				.id(item.getId())
				.materialTypeName(item.getName())
				.build())
		.collect(Collectors.toList());
	}
}
