package com.senla.finance.project.model.finnhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senla.finance.project.model.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    @Id
    private String symbol;
    private String currency;
    private String description;
    private String type;

    @ManyToMany(mappedBy = "companies")
    private Set<User> users = new HashSet<>();
}
