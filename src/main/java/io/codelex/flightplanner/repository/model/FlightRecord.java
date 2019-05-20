package io.codelex.flightplanner.repository.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "flights")
@SequenceGenerator(name = "seq_gen", sequenceName = "flights_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FlightRecord {
    @Id
    @GeneratedValue(generator = "seq_gen", strategy = SEQUENCE)
    private Long id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "airport_from")
    private AirportRecord from;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "airport_to")
    private AirportRecord to;

    private String carrier;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
}
