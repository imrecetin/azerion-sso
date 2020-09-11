package com.azerion.sso.controller.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "creationDate")
@Relation(collectionRelation = "creationDates")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreationDateResource extends RepresentationModel<CreationDateResource> {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
