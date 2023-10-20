package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.IResourceApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.resource.CreateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.resource.UpdateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.resource.CreateResourceCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.resource.UpdateResourceCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.resource.RetrieveResourceQueryResponse;
import com.ctu.se.oda.model11.models.resource.CreateResourceRequest;
import com.ctu.se.oda.model11.models.resource.UpdateResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceApi {
    @Autowired
    private IMainMapper<CreateResourceRequest, CreateResourceCommandRequest> createResourceMapper;
    @Autowired
    private IMainMapper<UpdateResourceRequest, UpdateResourceCommandRequest> updateResourceMapper;
    @Autowired
    private IResourceApplication resourceApplication;

    @PostMapping("")
    public ResponseEntity<CreateResourceCommandResponse> createResource(@RequestBody CreateResourceRequest createResourceRequest) {
        return new ResponseEntity<>(
                this.resourceApplication.createResource(this.createResourceMapper.convert(createResourceRequest)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<UpdateResourceCommandResponse> updateResource(@RequestBody UpdateResourceRequest updateResourceRequest, @PathVariable String resourceId) {
        return new ResponseEntity<>(
                this.resourceApplication.updateResource(this.updateResourceMapper.convert(updateResourceRequest), Long.valueOf(resourceId)),
                HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<List<RetrieveResourceQueryResponse>> listResource() {
        return new ResponseEntity<>(
                this.resourceApplication.listResource(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<RetrieveResourceQueryResponse> detailResource(@PathVariable String resourceId) {
        return new ResponseEntity<>(
                this.resourceApplication.detailResource(Long.valueOf(resourceId)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable String resourceId) {
        this.resourceApplication.deleteResource(Long.valueOf(resourceId));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteAllResource() {
        this.resourceApplication.deleteAllResource();
        return ResponseEntity.noContent().build();
    }
}
