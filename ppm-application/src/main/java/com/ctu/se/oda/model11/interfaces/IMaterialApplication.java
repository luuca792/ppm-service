package com.ctu.se.oda.model11.interfaces;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

public interface IMaterialApplication {
    void createMaterial(CreateMaterialCommandRequest createMaterialCommandRequest);
    void updateMaterial(UpdateMaterialCommandRequest updateMaterialCommandRequest);
    List<RetrieveMaterialQueryResponse> listMaterial();
    RetrieveMaterialQueryResponse detailMaterial(UUID material);
    void deleteMaterial(UUID material);
}
