package com.example.restjpa.vehiclelogistics.service;

import com.example.restjpa.vehiclelogistics.repository.VehicleRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * VehiclesApiDelegateImpl
 */
@Service
public class VehiclesApiDelegateImpl {

    final VehicleRepo vehicleRepo;

    public VehiclesApiDelegateImpl(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public List<Integer> getVehicleList(BigDecimal bottomLatitude, BigDecimal topLatitude, BigDecimal leftLongitude, BigDecimal rightLongitude) {

        List<Integer> vehicles = vehicleRepo.getAllVehicleInRectangle(bottomLatitude, topLatitude, leftLongitude, rightLongitude);

        return vehicles;
    }

    public List<Integer> getAllVehicleList() {

        System.out.println("get All !!!");
        List<Integer> vehicles = vehicleRepo.getAllVehicleId();

        return vehicles;
    }
}
