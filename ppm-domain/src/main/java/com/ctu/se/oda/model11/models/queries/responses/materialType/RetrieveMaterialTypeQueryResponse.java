package com.ctu.se.oda.model11.models.queries.responses.materialType;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrieveMaterialTypeQueryResponse {
	
	private UUID id;
	private String materialTypeName;

}
