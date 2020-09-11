package com.azerion.sso.controller.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "error")
@Relation(collectionRelation = "errors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResource {
    private String name;
    private String message;
    private String reasonPhrase;
}
