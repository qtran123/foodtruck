package com.assignment.foodtruck.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Pair {
    private Double distanceInKM;
    private FoodTruck foodTruck;
}
