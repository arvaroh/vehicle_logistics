package com.example.restjpa.vehiclelogistics;

import com.example.restjpa.vehiclelogistics.repository.VehicleRepo;
import com.example.restjpa.vehiclelogistics.service.VehiclesApiDelegateImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceLayerTest {

    @MockBean
    private VehicleRepo vehicleRepo;

    @Autowired
    private VehiclesApiDelegateImpl vehiclesApiDelegateImpl;

    @Test
    public void getVehiclesInRectangle() {
        // given borders of rectangle as botLat ... rightLon
        BigDecimal botLat = new BigDecimal(48);
        BigDecimal topLat = new BigDecimal(52);
        BigDecimal leftLon = new BigDecimal(36);
        BigDecimal rightLon = new BigDecimal(40);
        given(this.vehicleRepo.getAllVehicleInRectangle(botLat, topLat, leftLon, rightLon)).willReturn(Arrays.asList(3, 7, 11));

        // when
        List<Integer> actual = vehiclesApiDelegateImpl.getVehicleList(
                new BigDecimal(48), new BigDecimal(52), new BigDecimal(36), new BigDecimal(40)
        );

        // then
        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(Arrays.asList(3, 7, 11));
    }
}
