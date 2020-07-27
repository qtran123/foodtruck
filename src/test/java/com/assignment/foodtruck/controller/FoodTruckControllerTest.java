package com.assignment.foodtruck.controller;

import com.assignment.foodtruck.domain.FoodTruck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FoodTruckControllerTest {
    @Autowired
    private FoodTruckController controller;

    @Test
    public void contexLoads() throws Exception {

    }
    @Test
    public void givenBadPoints_whenQueryByPoint_receiveErrors(){
        assertThrows(IllegalArgumentException.class,
                () -> controller.getFoodTrucksByCoordinates(-137.7192002177133, -122.395976731096, 0));
        assertThrows(IllegalArgumentException.class,
                () -> controller.getFoodTrucksByCoordinates(37.7192002177133, -922.395976731096, 0));
    }
    @Test
    public void givenValidPoints_whenQueryByPoint_receiveFiveResults(){
        List<FoodTruck> result = controller.getFoodTrucksByCoordinates(37.7192002177133, -122.395976731096, 5);
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getApplicant()).isEqualTo("Singh Brothers Ice Cream");
        assertThat(result.get(0).getAddress()).isEqualTo("1060 KEY AVE");

        assertThat(result.get(1).getApplicant()).isEqualTo("Singh Brothers Ice Cream");
        assertThat(result.get(1).getAddress()).isEqualTo("3444 JENNINGS ST");

        assertThat(result.get(2).getApplicant()).isEqualTo("Singh Brothers Ice Cream");
        assertThat(result.get(2).getAddress()).isEqualTo("1173 INGERSON AVE");

        assertThat(result.get(3).getApplicant()).isEqualTo("May Catering");
        assertThat(result.get(3).getAddress()).isEqualTo("1098 LE CONTE AVE");

        assertThat(result.get(4).getApplicant()).isEqualTo("May Catering");
        assertThat(result.get(4).getAddress()).isEqualTo("1075 LE CONTE AVE");
    }
}