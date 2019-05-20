package io.codelex.flightplanner.repository.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "airports")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AirportRecord {
    @Id
    private String airport;
    private String country;
    private String city;
}
