package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.entities.MaterialType;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;
import com.ctu.se.oda.model11.repositories.IMaterialRepository;
import com.ctu.se.oda.model11.repositories.IMaterialTypeRepository;
import com.ctu.se.oda.model11.utils.ModelMapperUtil;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@Validated
public class MaterialDAO implements IMaterialService {
	
	@Autowired
	private IMaterialRepository materialRepository;
	@Autowired
	private IMaterialTypeRepository materialTypeRepository;
	@Autowired
	private IInfrastructureMapper<CreateMaterialCommandRequest, Material> createMaterialEntityMapper;
	@Autowired
	private IInfrastructureMapper<UpdateMaterialCommandRequest, Material> updateMaterialEntityMapper;

	@Override
	public void createMaterial(@Valid CreateMaterialCommandRequest createMaterialCommandRequest) {
		Optional<MaterialType> materialType = materialTypeRepository.findById(createMaterialCommandRequest.getMaterialType());
		if (!materialType.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_TYPE_ID_NOT_FOUND);
		}
		if (createMaterialCommandRequest.getMaterialName().isBlank()) {
			throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_NAME_IS_NULL);
		}
		Material material = createMaterialEntityMapper.convert(createMaterialCommandRequest);
		material.setMaterialType(materialType.get());
		materialRepository.save(material);
	}

	@Override
	public void updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest) {
		var retrieveMaterial = materialRepository.findById(updateMaterialCommandRequest.getMaterialId());
		if (retrieveMaterial.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_ID_NOT_FOUND);
		}
		Material updatedMaterial = updateMaterialEntityMapper.convert(updateMaterialCommandRequest);
		ModelMapperUtil.copy(updatedMaterial, retrieveMaterial.get());
		materialRepository.save(retrieveMaterial.get());
	}

	@Override
	public List<RetrieveMaterialQueryResponse> listMaterial() {
		return materialRepository.findAll().stream()
				.map(material -> RetrieveMaterialQueryResponse.builder()
				.materialId(material.getId())
				.materialName(material.getName())
				.materialTypeName(material.getMaterialType().getId())
				.build())
				.collect(Collectors.toList());
	}

	@Override
	public RetrieveMaterialQueryResponse detailMaterial(UUID materialId) {
		var retrieveMaterial = materialRepository.findById(materialId).get();
		return RetrieveMaterialQueryResponse.builder()
				.materialId(retrieveMaterial.getId())
				.materialName(retrieveMaterial.getName())
				.materialTypeName(retrieveMaterial.getMaterialType().getId())
				.build();
	}

	@Override
	public void deleteMaterial(UUID material) {
		materialRepository.deleteById(material);
	}
}
