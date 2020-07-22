package com.example.restjpa.vehiclelogistics.service;

import com.example.restjpa.vehiclelogistics.exception.LocationSaveException;
import com.example.restjpa.vehiclelogistics.model.Vehicle;
import com.example.restjpa.vehiclelogistics.model.VehicleDto;
import com.example.restjpa.vehiclelogistics.repository.VehicleRepo;
import org.springframework.stereotype.Service;

@Service
public class LocationApiDelegateImpl {

    final
    VehicleRepo vehicleRepo;

    public LocationApiDelegateImpl(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public void createCarLocation(VehicleDto vehicleDto) {

        Vehicle vehicle = new Vehicle(vehicleDto);
        try {
            vehicleRepo.saveAndFlush(vehicle);
        } catch (Exception e) {
            throw new LocationSaveException(String.format("vehicle '%s' isn't stored:  %s", vehicle.getVehicleId(), e.getMessage()));
        }
    }

}
