package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.TodoController;
import com.azerion.sso.controller.resource.TodoResource;
import com.azerion.sso.model.Todo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public  abstract  class TodoResourceAssembler extends RepresentationModelAssemblerSupport<Todo,TodoResource> {

    private CreationDateResourceAssembler creationDateModelAssembler;

    public TodoResourceAssembler(TodoController controller, CreationDateResourceAssembler creationDateModelAssembler) {
        super(controller.getClass(),TodoResource.class);
        this.creationDateModelAssembler=creationDateModelAssembler;
    }

    public Todo toEntity(TodoResource resource) {
        Todo todo=Todo.builder().name(resource.getName()).id(resource.getId()).creationDate(creationDateModelAssembler.toEntity(resource.getCreationDateResource())).build();
        return todo;
    }

    @Override
    public TodoResource toModel(Todo entity) {

        TodoResource todoResource= TodoResource.builder().name(entity.getName()).id(entity.getId())
                .creationDateResource(creationDateModelAssembler.toModel(entity.getCreationDate())).build();

        Link lnk = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TodoController.class).save(todoResource))
                .withSelfRel();

        return todoResource.add(lnk);
    }

}
