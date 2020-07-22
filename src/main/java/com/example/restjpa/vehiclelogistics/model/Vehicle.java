package com.example.restjpa.vehiclelogistics.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "vehicles",
        indexes = {@Index(name = "latitude_index", columnList = "latitude"),
                @Index(name = "longitude_index", columnList = "longitude")})
public class Vehicle implements Serializable {

    @Id
    private int vehicleId;

    @NotNull(message = "latitude is mandatory")
    @DecimalMin("-90.00")
    @DecimalMax("90.00")
    private BigDecimal latitude;

    @NotNull(message = "longitude is mandatory")
    @DecimalMin("-180.00")
    @DecimalMax("180.00")
    private BigDecimal longitude;

    public Vehicle(VehicleDto vehicleDto) {
        this.vehicleId = vehicleDto.getVehicleId();
        this.latitude = vehicleDto.getLatitude();
        this.longitude = vehicleDto.getLongitude();
    }
}
