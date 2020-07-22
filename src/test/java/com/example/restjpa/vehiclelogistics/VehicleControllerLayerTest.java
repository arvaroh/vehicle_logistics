package com.example.restjpa.vehiclelogistics;

import com.example.restjpa.vehiclelogistics.controller.VehicleController;
import com.example.restjpa.vehiclelogistics.service.LocationApiDelegateImpl;
import com.example.restjpa.vehiclelogistics.service.VehiclesApiDelegateImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc
public class VehicleControllerLayerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VehiclesApiDelegateImpl vehiclesApiDelegate;
    @MockBean
    private LocationApiDelegateImpl locationApiDelegate;

    @Test
    public void getResponseForVehiclesInRectangle() throws Exception {
        // given borders of rectangle as botLat ... rightLon
        BigDecimal botLat = new BigDecimal(48);
        BigDecimal topLat = new BigDecimal(52);
        BigDecimal leftLon = new BigDecimal(36);
        BigDecimal rightLon = new BigDecimal(40);
        given(this.vehiclesApiDelegate.getVehicleList(botLat, topLat, leftLon, rightLon)).willReturn(Arrays.asList(3, 7, 11));

        // when - then
        mvc.perform(get("/vehicle/vehicles?bottomLatitude=48&leftLongitude=36&rightLongitude=40&topLatitude=52")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2]", is(11)));
    }

}
