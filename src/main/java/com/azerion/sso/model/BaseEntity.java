package com.azerion.sso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity<T>  implements Serializable {
    private T id;
    private Long version;
}
