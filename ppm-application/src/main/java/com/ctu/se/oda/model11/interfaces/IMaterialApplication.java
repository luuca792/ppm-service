package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

import java.util.List;
import java.util.UUID;

public interface IMaterialApplication {
    CreateMaterialCommandResponse createMaterial(CreateMaterialCommandRequest createMaterialCommandRequest);
    UpdateMaterialCommandResponse updateMaterial(UpdateMaterialCommandRequest updateMaterialCommandRequest);
    List<RetrieveMaterialQueryResponse> listMaterial();
    RetrieveMaterialQueryResponse detailMaterial(UUID material);
    void deleteMaterial(UUID material);
}
