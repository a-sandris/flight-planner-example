package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.repository.model.FlightRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FlightRepository extends JpaRepository<FlightRecord, Long> {
    @Query("select count(flight) > 0 from FlightRecord flight" +
            " where flight.from.airport = :from and flight.to.airport = :to" +
            " and flight.carrier = :carrier" +
            " and flight.departureTime = :departureTime and flight.arrivalTime = :arrivalTime")
    boolean flightExists(@Param("from") String from,
                         @Param("to") String to,
                         @Param("carrier") String carrier,
                         @Param("departureTime") LocalDateTime departureTime,
                         @Param("arrivalTime") LocalDateTime arrivalTime);

    default Page<FlightRecord> findFlights(@Param("from") String from,
                                           @Param("to") String to,
                                           @Param("departureDate") LocalDate departureDate,
                                           Pageable pageable) {
        return findFlights(from, to, departureDate.atStartOfDay(), departureDate.plusDays(1).atStartOfDay(), pageable);
    }

    @Query("select flight from FlightRecord flight" +
            " where flight.from.airport = :from and flight.to.airport = :to" +
            " and flight.departureTime >= :departureDateFrom and flight.departureTime < :departureDateTo")
    Page<FlightRecord> findFlights(@Param("from") String from,
                                   @Param("to") String to,
                                   @Param("departureDateFrom") LocalDateTime departureDateFrom,
                                   @Param("departureDateTo") LocalDateTime departureDateTo,
                                   Pageable pageable);

    @Transactional
    @Modifying
    @Override
    @Query("delete from FlightRecord flight where flight.id = :id")
    void deleteById(@Param("id") Long id);
}
