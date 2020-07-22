package com.example.restjpa.vehiclelogistics;

import com.example.restjpa.vehiclelogistics.model.Vehicle;
import com.example.restjpa.vehiclelogistics.repository.VehicleRepo;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    VehicleRepo vehicleRepo;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void foundVehicleInRectangle() {
        // given
        for (int i = 0; i < 5; i++) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(i);
            vehicle.setLatitude(new BigDecimal(50 + i));
            vehicle.setLongitude(new BigDecimal(36 + i));
            vehicleRepo.save(vehicle);
        }

        // when
        List<Integer> actual = vehicleRepo.getAllVehicleInRectangle(
                new BigDecimal(48), new BigDecimal(52), new BigDecimal(36), new BigDecimal(40)
        );

        // expected
        List<Integer> expected = Arrays.asList(0, 1, 2);

        // then
        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void setOutOfBandDataShouldException() {
        // given
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(123);
        // when
        vehicle.setLatitude(new BigDecimal(91)); // out-of-band data
        vehicle.setLongitude(new BigDecimal(36));

        // expected
        thrown.expect(ConstraintViolationException.class);
    }

}
