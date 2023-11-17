package com.ctu.se.oda.model11.models.commands.responses.materialType;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMaterialTypeCommandResponse {
	
	private UUID id;
	private String materialName;

}
