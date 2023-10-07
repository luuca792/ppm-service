package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.IDomainModel;
import com.ctu.se.oda.model11.models.IModel;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.project.CreateProjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateProjectRequestMapper implements IMainMapper {
    @Override
    public IDomainModel convert(IModel requestModel) {
        var request = (CreateProjectRequest) requestModel;
        return CreateProjectCommandRequest.createInstance(request.getName(),
                request.getDuration(), request.getCreatorId());
    }
}
