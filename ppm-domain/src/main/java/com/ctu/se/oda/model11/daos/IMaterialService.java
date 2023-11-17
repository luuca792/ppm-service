package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

import jakarta.validation.Valid;

public interface IMaterialService {
    void createMaterial(@Valid CreateMaterialCommandRequest createMaterialCommandRequest);
    void updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest);
    List<RetrieveMaterialQueryResponse> listMaterial();
    RetrieveMaterialQueryResponse detailMaterial(UUID materialId);
    void deleteMaterial(UUID materialId);
}
