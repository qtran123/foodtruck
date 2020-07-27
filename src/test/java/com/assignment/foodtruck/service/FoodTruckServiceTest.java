package com.assignment.foodtruck.service;

import com.assignment.foodtruck.domain.FoodTruck;
import com.assignment.foodtruck.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FoodTruckServiceTest {

    @MockBean
    private InMemoryRepository mockedInMemoryRepository;

    private FoodTruckService subject;

    @BeforeEach
    void setUp() {
        subject = new FoodTruckService(mockedInMemoryRepository);
    }

    @Test
    public void givenBadPoints_throwsException() {
        // Invalid Latitude
        assertThrows(IllegalArgumentException.class, () -> subject.findClosestFoodTrucksToPoint(-122.0, 120.0,0));
        // Invalid Longitude
        assertThrows(IllegalArgumentException.class, () -> subject.findClosestFoodTrucksToPoint(37.0, 197.0,0));
        // Null Latitude
        assertThrows(IllegalArgumentException.class, () -> subject.findClosestFoodTrucksToPoint(null, -122.0,0));
        // Null Longitude
        assertThrows(IllegalArgumentException.class, () -> subject.findClosestFoodTrucksToPoint(37.0, null,0));
    }

    @Test
    void givenTenItems_whenGetClosestToPoint_returnsTopFiveFoodTruck() {
        Mockito.when(mockedInMemoryRepository.getFoodTrucks()).
                thenReturn(setupTenFoodTruckList());

        List<FoodTruck> result = subject.findClosestFoodTrucksToPoint(37.0, -122.0, 5);

        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getApplicant()).isEqualTo("Food Truck 0");
        assertThat(result.get(1).getApplicant()).isEqualTo("Food Truck 1");
        assertThat(result.get(2).getApplicant()).isEqualTo("Food Truck 2");
        assertThat(result.get(3).getApplicant()).isEqualTo("Food Truck 3");
        assertThat(result.get(4).getApplicant()).isEqualTo("Food Truck 4");
    }
    @Test
    void givenTenItems_whenGetClosestToPointWithSuperMaxValue_returnsTopFiveFoodTruck() {
        Mockito.when(mockedInMemoryRepository.getFoodTrucks()).
                thenReturn(setupTenFoodTruckList());

        List<FoodTruck> result = subject.findClosestFoodTrucksToPoint(37.0, -122.0, 100);

        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getApplicant()).isEqualTo("Food Truck 0");
        assertThat(result.get(1).getApplicant()).isEqualTo("Food Truck 1");
        assertThat(result.get(2).getApplicant()).isEqualTo("Food Truck 2");
        assertThat(result.get(3).getApplicant()).isEqualTo("Food Truck 3");
        assertThat(result.get(4).getApplicant()).isEqualTo("Food Truck 4");
        assertThat(result.get(5).getApplicant()).isEqualTo("Food Truck 5");
        assertThat(result.get(6).getApplicant()).isEqualTo("Food Truck 6");
        assertThat(result.get(7).getApplicant()).isEqualTo("Food Truck 7");
        assertThat(result.get(8).getApplicant()).isEqualTo("Food Truck 8");
        assertThat(result.get(9).getApplicant()).isEqualTo("Food Truck 9");
    }

    @Test
    void givenTenItems_whenGetClosestToPoint2_returnsTopFiveFoodTruck() {
        Mockito.when(mockedInMemoryRepository.getFoodTrucks()).
                thenReturn(setupTenFoodTruckList());

        List<FoodTruck> result = subject.findClosestFoodTrucksToPoint(37.804051430826, -122.437134422674, 5);

        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getApplicant()).isEqualTo("Food Truck 9");
        assertThat(result.get(1).getApplicant()).isEqualTo("Food Truck 8");
        assertThat(result.get(2).getApplicant()).isEqualTo("Food Truck 7");
        assertThat(result.get(3).getApplicant()).isEqualTo("Food Truck 6");
        assertThat(result.get(4).getApplicant()).isEqualTo("Food Truck 5");
    }

    @Test
    void givenOneFoodTruck_whenGetFoodTrucks_returnsOneFoodTruck() {
        Mockito.when(mockedInMemoryRepository.getFoodTrucks()).
                thenReturn(Collections.singletonList(
                        FoodTruck
                                .builder()
                                .locationId(1334938L)
                                .applicant("D & T Catering")
                                .latitude(37.795051430826)
                                .longitude(-122.446134422674)
                                .build()));

        List<FoodTruck> result = subject.getFoodTrucks();
        assertThat(result.size()).isEqualTo(1);
    }

    private List<FoodTruck> setupTenFoodTruckList() {
        List<FoodTruck> foodTrucks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            double newLat = 37.795051430826 + (.001*i);
            double newLong = -122.446134422674 + (.001*i);
            foodTrucks.add(FoodTruck
                    .builder()
                    .locationId(1334938L + i)
                    .applicant("Food Truck " + i)
                    .latitude(newLat)
                    .longitude(newLong)
                    .build());
        }
        return foodTrucks;
    }

    ;
}