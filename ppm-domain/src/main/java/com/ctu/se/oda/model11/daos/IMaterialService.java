package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface IMaterialService {
    CreateMaterialCommandResponse createMaterial(@Valid CreateMaterialCommandRequest createMaterialCommandRequest);
    UpdateMaterialCommandResponse updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest);
    List<RetrieveMaterialQueryResponse> listMaterial();
    RetrieveMaterialQueryResponse detailMaterial(UUID materialId);
    void deleteMaterial(UUID materialId);
}
