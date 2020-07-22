package com.example.restjpa.vehiclelogistics.repository;

import com.example.restjpa.vehiclelogistics.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    @Query("select vehicleId from Vehicle")
    List<Integer> getAllVehicleId();

    @Query("select vehicleId from Vehicle where latitude between :botLat and :topLat and longitude between :botLon and :topLon")
    List<Integer> getAllVehicleInRectangle(@Param("botLat") BigDecimal bottomLatitude, @Param("topLat") BigDecimal topLatitude,
                                           @Param("botLon") BigDecimal leftLongitude, @Param("topLon") BigDecimal rightLongitude);
}
