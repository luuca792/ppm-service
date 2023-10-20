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
        return this.createMaterialEntityMapper.reverse(
                this.materialRepository.save(this.createMaterialEntityMapper.convert(createMaterialCommandRequest))
        );
    }

    @Override
    public UpdateMaterialCommandResponse updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest, Long materialId) {
        var retrieveMaterialOptional = this.materialRepository.findById(materialId);
        if (retrieveMaterialOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrieveMaterial = retrieveMaterialOptional.get().getId();
        updateMaterialCommandRequest.setMaterialId(retrieveMaterial);
        return this.updateMaterialEntityMapper.reverse(
                this.materialRepository.save(this.updateMaterialEntityMapper.convert(updateMaterialCommandRequest))
        );
    }

    @Override
    public List<RetrieveMaterialQueryResponse> listMaterial() {
        return this.materialRepository.findAll().stream()
                .map(material -> RetrieveMaterialQueryResponse.builder().materialId(material.getId()).materialName(material.getName()).materialType(material.getType()).build())
                .collect(Collectors.toList());
    }

    @Override
    public RetrieveMaterialQueryResponse detailMaterial(Long materialId) {
        var retrieveMaterial = this.materialRepository.findById(materialId).get();
        return RetrieveMaterialQueryResponse.builder()
                .materialId(retrieveMaterial.getId())
                .materialName(retrieveMaterial.getName())
                .materialType(retrieveMaterial.getType())
                .build();
    }

    @Override
    public void deleteMaterial(Long materialId) {
        this.materialRepository.deleteById(materialId);
    }

    @Override
    public void deleteAllMaterial() {
        this.materialRepository.deleteAll();
    }
}
