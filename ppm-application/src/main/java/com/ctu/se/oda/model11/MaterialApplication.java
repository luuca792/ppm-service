package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.IMaterialService;
import com.ctu.se.oda.model11.interfaces.IMaterialApplication;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class MaterialApplication implements IMaterialApplication {

    @Autowired
    private IMaterialService materialService;

    @Override
    public CreateMaterialCommandResponse createMaterial(CreateMaterialCommandRequest createMaterialCommandRequest) {
        return materialService.createMaterial(createMaterialCommandRequest);
    }

    @Override
    public UpdateMaterialCommandResponse updateMaterial(UpdateMaterialCommandRequest updateMaterialCommandRequest, UUID materialId) {
        return materialService.updateMaterial(updateMaterialCommandRequest, materialId);
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
