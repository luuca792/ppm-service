package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

import java.util.List;

public interface IMaterialApplication {
    CreateMaterialCommandResponse createMaterial(CreateMaterialCommandRequest createMaterialCommandRequest);
    UpdateMaterialCommandResponse updateMaterial(UpdateMaterialCommandRequest updateMaterialCommandRequest, Long materialId);
    List<RetrieveMaterialQueryResponse> listMaterial();
    RetrieveMaterialQueryResponse detailMaterial(Long materialId);
    void deleteMaterial(Long materialId);
    void deleteAllMaterial();
}
