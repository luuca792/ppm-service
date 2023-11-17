package com.ctu.se.oda.model11;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.IMaterialService;
import com.ctu.se.oda.model11.interfaces.IMaterialApplication;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class MaterialApplication implements IMaterialApplication {

    @Autowired
    private IMaterialService materialService;

    @Override
    public void createMaterial(CreateMaterialCommandRequest createMaterialCommandRequest) {
        materialService.createMaterial(createMaterialCommandRequest);
    }

    @Override
    public void updateMaterial(UpdateMaterialCommandRequest updateMaterialCommandRequest) {
        materialService.updateMaterial(updateMaterialCommandRequest);
    }

    @Override
    public List<RetrieveMaterialQueryResponse> listMaterial() {
        return materialService.listMaterial();
    }

    @Override
    public RetrieveMaterialQueryResponse detailMaterial(UUID materialId) {
        return materialService.detailMaterial(materialId);
    }

    @Override
    public void deleteMaterial(UUID materialId) {
        materialService.deleteMaterial(materialId);
    }

}
