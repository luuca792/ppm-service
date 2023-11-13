package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;
import com.ctu.se.oda.model11.repositories.IMaterialRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@NoArgsConstructor
@Validated
public class MaterialDAO implements IMaterialService{
    @Autowired
    private IMaterialRepository materialRepository;
    @Autowired
    private IInfrastructureMapper<CreateMaterialCommandRequest, Material, CreateMaterialCommandResponse> createMaterialEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateMaterialCommandRequest, Material, UpdateMaterialCommandResponse> updateMaterialEntityMapper;

    @Override
    public CreateMaterialCommandResponse createMaterial(@Valid CreateMaterialCommandRequest createMaterialCommandRequest) {
        return createMaterialEntityMapper.reverse(
                materialRepository.save(createMaterialEntityMapper.convert(createMaterialCommandRequest))
        );
    }

    @Override
    public UpdateMaterialCommandResponse updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest) {
        var retrieveMaterial = materialRepository.findById(updateMaterialCommandRequest.getMaterialId());
        if (retrieveMaterial.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        return updateMaterialEntityMapper.reverse(
                materialRepository.save(updateMaterialEntityMapper.convert(updateMaterialCommandRequest))
        );
    }

    @Override
    public List<RetrieveMaterialQueryResponse> listMaterial() {
        return materialRepository.findAll().stream()
                .map(material -> RetrieveMaterialQueryResponse.builder()
                        .materialId(material.getId())
                        .materialName(material.getName())
                        .materialTypeName(String.valueOf(material.getMaterialTypeName()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public RetrieveMaterialQueryResponse detailMaterial(UUID materialId) {
        var retrieveMaterial = materialRepository.findById(materialId).get();
        return RetrieveMaterialQueryResponse.builder()
                .materialId(retrieveMaterial.getId())
                .materialName(retrieveMaterial.getName())
                .materialTypeName(String.valueOf(retrieveMaterial.getMaterialTypeName()))
                .build();
    }

    @Override
    public void deleteMaterial(UUID material) {
        materialRepository.deleteById(material);
    }
}
