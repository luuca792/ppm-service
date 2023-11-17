package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@Validated
public class MaterialDAO implements IMaterialService{
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
    		throw new IllegalArgumentException(CustomErrorMessage.MATERIAL_TYPE_ID_NOT_FOUND);
    	}
    	Material material = createMaterialEntityMapper.convert(createMaterialCommandRequest);
        material.setMaterialType(materialType.get());
		materialRepository.save(material);
    }

    @Override
    public void updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest) {
        var retrieveMaterial = materialRepository.findById(updateMaterialCommandRequest.getMaterialId());
        if (retrieveMaterial.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        materialRepository.save(updateMaterialEntityMapper.convert(updateMaterialCommandRequest));
    }

    @Override
    public List<RetrieveMaterialQueryResponse> listMaterial() {
        return materialRepository.findAll().stream()
                .map(material -> RetrieveMaterialQueryResponse.builder()
                        .materialId(material.getId())
                        .materialName(material.getName())
                        .materialTypeName(String.valueOf(material.getMaterialType()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public RetrieveMaterialQueryResponse detailMaterial(UUID materialId) {
        var retrieveMaterial = materialRepository.findById(materialId).get();
        return RetrieveMaterialQueryResponse.builder()
                .materialId(retrieveMaterial.getId())
                .materialName(retrieveMaterial.getName())
                .materialTypeName(String.valueOf(retrieveMaterial.getMaterialType()))
                .build();
    }

    @Override
    public void deleteMaterial(UUID material) {
        materialRepository.deleteById(material);
    }
}
