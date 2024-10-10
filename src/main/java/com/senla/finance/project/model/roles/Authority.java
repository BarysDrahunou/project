package com.senla.finance.project.model.roles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Authority {

    @Id
    String authority;
}
