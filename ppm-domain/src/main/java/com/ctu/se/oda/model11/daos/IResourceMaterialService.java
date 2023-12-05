package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.dtos.ResourceMaterialDTO;

import java.util.List;
import java.util.UUID;

public interface IResourceMaterialService {
    List<ResourceMaterialDTO> getAllByResourceId(UUID resourceId);
}
