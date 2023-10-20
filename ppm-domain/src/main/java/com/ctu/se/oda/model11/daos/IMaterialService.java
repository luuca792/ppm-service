package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

import jakarta.validation.Valid;

import java.util.List;

public interface IMaterialService {
    CreateMaterialCommandResponse createMaterial(@Valid CreateMaterialCommandRequest createMaterialCommandRequest);
    UpdateMaterialCommandResponse updateMaterial(@Valid UpdateMaterialCommandRequest updateMaterialCommandRequest, Long materialId);
    List<RetrieveMaterialQueryResponse> listMaterial();
    RetrieveMaterialQueryResponse detailMaterial(Long materialId);
    void deleteMaterial(Long materialId);
    void deleteAllMaterial();
}
