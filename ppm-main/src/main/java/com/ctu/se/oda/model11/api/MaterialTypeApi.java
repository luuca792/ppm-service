package com.ctu.se.oda.model11.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private IMainMapper<CreateMaterialTypeRequest, CreateMaterialTypeCommandRequest> mapper; 
	
	@GetMapping
	public ResponseEntity<List<RetrieveMaterialTypeQueryResponse>> getAllMaterialTypes() {
		List<RetrieveMaterialTypeQueryResponse> materialTypes = application.getAllMaterialType();
		return new ResponseEntity<List<RetrieveMaterialTypeQueryResponse>>(materialTypes, HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> createMaterialType(@RequestBody CreateMaterialTypeRequest request) {
		application.createMaterial(mapper.convert(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
