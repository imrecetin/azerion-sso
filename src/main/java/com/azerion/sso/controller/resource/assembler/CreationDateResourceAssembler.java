package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.TodoController;
import com.azerion.sso.controller.resource.CreationDateResource;
import com.azerion.sso.model.CreationDate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.time.LocalDateTime;
import java.util.Objects;


public abstract class CreationDateResourceAssembler extends RepresentationModelAssemblerSupport<CreationDate, CreationDateResource> {

    public CreationDateResourceAssembler(TodoController controller) {
        super(controller.getClass(),CreationDateResource.class);
    }

    public CreationDate toEntity(CreationDateResource resource) {
        CreationDate creationDate=CreationDate.builder().createdDate(LocalDateTime.now()).build();
        if(!Objects.isNull(resource)) {
            creationDate.setUpdatedDate(resource.getUpdatedDate());
            creationDate.setCreatedDate(resource.getCreatedDate());
        }
        return creationDate;
    }

    @Override
    public CreationDateResource toModel(CreationDate entity) {
        CreationDateResource creationDateModel=CreationDateResource.builder().build();
        if (!Objects.isNull(entity)){
            creationDateModel.setCreatedDate(entity.getCreatedDate());
            creationDateModel.setUpdatedDate(entity.getUpdatedDate());
        }
        return creationDateModel;
    }
}
