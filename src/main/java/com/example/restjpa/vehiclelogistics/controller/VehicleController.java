package com.example.restjpa.vehiclelogistics.controller;

import com.example.restjpa.vehiclelogistics.api.LocationApi;
import com.example.restjpa.vehiclelogistics.api.VehiclesApi;
import com.example.restjpa.vehiclelogistics.model.VehicleDto;
import com.example.restjpa.vehiclelogistics.service.LocationApiDelegateImpl;
import com.example.restjpa.vehiclelogistics.service.VehiclesApiDelegateImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController implements VehiclesApi, LocationApi {

    final VehiclesApiDelegateImpl vehiclesApiDelegate;
    final LocationApiDelegateImpl locationApiDelegate;

    public VehicleController(VehiclesApiDelegateImpl vehiclesApiDelegate, LocationApiDelegateImpl locationApiDelegate) {
        this.vehiclesApiDelegate = vehiclesApiDelegate;
        this.locationApiDelegate = locationApiDelegate;
    }

    @GetMapping("/swagger")
    public RedirectView redirect() {
        return new RedirectView("/swagger-ui.html");
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Integer>> getAll() {
        return ResponseEntity.ok(vehiclesApiDelegate.getAllVehicleList());
    }

    @Override
    public ResponseEntity<Void> createCarLocation(@Valid @RequestBody VehicleDto vehicleDto) {
        locationApiDelegate.createCarLocation(vehicleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Integer>> getVehicleList(@NotNull @DecimalMin("-90.0") @DecimalMax("90.0") @Valid BigDecimal bottomLatitude,
                                                        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") @Valid BigDecimal topLatitude,
                                                        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") @Valid BigDecimal leftLongitude,
                                                        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") @Valid BigDecimal rightLongitude) {
        List<Integer> vehicles = vehiclesApiDelegate.getVehicleList(bottomLatitude, topLatitude, leftLongitude, rightLongitude);
        return ResponseEntity.ok(vehicles);
    }
}
