package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.IMaterialApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import com.ctu.se.oda.model11.models.material.CreateMaterialRequest;
import com.ctu.se.oda.model11.models.material.UpdateMaterialRequest;
import com.ctu.se.oda.model11.models.queries.responses.material.RetrieveMaterialQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<CreateMaterialCommandResponse> createMaterial(@RequestBody CreateMaterialRequest createMaterialRequest) {
        return new ResponseEntity<>(
                materialApplication.createMaterial(createMaterialMapper.convert(createMaterialRequest)),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{materialId}")
    public ResponseEntity<UpdateMaterialCommandResponse> updateMaterial(@RequestBody UpdateMaterialRequest updateMaterialRequest, @PathVariable UUID materialId) {
        updateMaterialRequest.setMaterialId(String.valueOf(materialId));
        return new ResponseEntity<>(
                materialApplication.updateMaterial(updateMaterialMapper.convert(updateMaterialRequest)),
                HttpStatus.OK
        );
    }
    @GetMapping()
    public ResponseEntity<List<RetrieveMaterialQueryResponse>> listMaterial() {
        return new ResponseEntity<>(
                materialApplication.listMaterial(),
                HttpStatus.OK
        );
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
        return new ResponseEntity<String>("Material is deleted", HttpStatus.OK);
    }
}
