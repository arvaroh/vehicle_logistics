package com.example.restjpa.vehiclelogistics;

import com.example.restjpa.vehiclelogistics.controller.VehicleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VehiclelogisticsApplicationTests {

    @Autowired
    private VehicleController vehicleController;

    @Test
    void contextLoads() {
        System.out.println("Test of contentLoad");
        assertThat(vehicleController).isNotNull();
    }

}
