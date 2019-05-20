package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.repository.model.AirportRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<AirportRecord, String> {
    default AirportRecord findOrCreate(String airport, String country, String city) {
        if (existsById(airport)) {
            return getOne(airport);
        }
        var record = AirportRecord.builder()
                .airport(airport)
                .country(country)
                .city(city)
                .build();
        return save(record);
    }

    @Query("select airport from AirportRecord airport" +
            " where lower(airport.airport) like lower(concat('%',trim(:search),'%'))" +
            " or lower(airport.country) like lower(concat('%',trim(:search),'%'))" +
            " or lower(airport.city) like lower(concat('%',trim(:search),'%'))")
    List<AirportRecord> search(@Param("search") String search);
}
