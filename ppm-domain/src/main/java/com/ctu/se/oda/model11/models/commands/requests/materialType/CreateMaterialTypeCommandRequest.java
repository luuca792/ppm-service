package com.ctu.se.oda.model11.models.commands.requests.materialType;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMaterialTypeCommandRequest {
	
	@NotBlank
	private String materialTypeName;

	@Override
	public String toString() {
		return "CreateMaterialTypeCommandRequest{" +
				"materialType"+ this.materialTypeName +"}";
	}
}
