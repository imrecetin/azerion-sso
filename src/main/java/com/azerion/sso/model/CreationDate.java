package com.azerion.sso.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreationDate {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
