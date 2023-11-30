package com.ctu.se.oda.model11.api;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.materialType.UpdateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.materialType.UpdateMaterialTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctu.se.oda.model11.interfaces.IMaterialTypeApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.materialType.CreateMaterialTypeRequest;
import com.ctu.se.oda.model11.models.queries.responses.materialType.RetrieveMaterialTypeQueryResponse;

@RestController
@RequestMapping("/materialtypes")
public class MaterialTypeApi {
	
	@Autowired
	private IMaterialTypeApplication application;
	
	@Autowired
	private IMainMapper<CreateMaterialTypeRequest, CreateMaterialTypeCommandRequest> createMapper;

	@Autowired
	private IMainMapper<UpdateMaterialTypeRequest, UpdateMaterialTypeCommandRequest> updateMapper;

	@GetMapping
	public ResponseEntity<List<RetrieveMaterialTypeQueryResponse>> getAllMaterialTypes() {
		List<RetrieveMaterialTypeQueryResponse> materialTypes = application.getAllMaterialType();
		return new ResponseEntity<List<RetrieveMaterialTypeQueryResponse>>(materialTypes, HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> createMaterialType(@RequestBody CreateMaterialTypeRequest request) {
		application.createMaterial(createMapper.convert(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{materialTypeId}")
	public ResponseEntity<?> updateMaterialType(@RequestBody UpdateMaterialTypeRequest request,
												@PathVariable String materialTypeId) {
		request.setMaterialTypeId(materialTypeId);
		application.updateMaterialType(updateMapper.convert(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{materialTypeId}")
	public ResponseEntity<?> deleteMaterialType(@PathVariable String materialTypeId) {
		application.deleteMaterialTypeById(UUID.fromString(materialTypeId));
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
