package com.azerion.sso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Objects;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Todo extends BaseEntity<Long> {

    private String name;
    private CreationDate creationDate;

    public boolean existCreationDate(){
        return !Objects.isNull(creationDate);
    }

}
