package com.azerion.sso.controller.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "todo")
@Relation(collectionRelation = "todos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoResource extends RepresentationModel<TodoResource> implements BaseResource<Long> {

    private Long id;
    private String name;
    private CreationDateResource creationDateResource;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}
