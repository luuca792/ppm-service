package com.ctu.se.oda.model11.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctu.se.oda.model11.interfaces.IMaterialApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.material.CreateMaterialRequest;
import com.ctu.se.oda.model11.models.material.UpdateMaterialRequest;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;

@RestController
@RequestMapping("/materials")
public class MaterialApi {

    @Autowired
    private IMainMapper<CreateMaterialRequest, CreateMaterialCommandRequest> createMaterialMapper;

    @Autowired
    private IMainMapper<UpdateMaterialRequest, UpdateMaterialCommandRequest> updateMaterialMapper;

    @Autowired
    private IMaterialApplication materialApplication;

    @PostMapping()
    public ResponseEntity<?> createMaterial(@RequestBody CreateMaterialRequest createMaterialRequest) {
        materialApplication.createMaterial(createMaterialMapper.convert(createMaterialRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{materialId}")
    public ResponseEntity<?> updateMaterial(@RequestBody UpdateMaterialRequest updateMaterialRequest, @PathVariable UUID materialId) {
        updateMaterialRequest.setMaterialId(materialId);
        materialApplication.updateMaterial(updateMaterialMapper.convert(updateMaterialRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<RetrieveMaterialQueryResponse>> listMaterial() {
        return new ResponseEntity<>(materialApplication.listMaterial(), HttpStatus.OK);
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<RetrieveMaterialQueryResponse> detailMaterial(@PathVariable UUID materialId) {
        return new ResponseEntity<>(
                materialApplication.detailMaterial(materialId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable UUID materialId) {
        materialApplication.deleteMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
}
