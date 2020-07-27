package com.assignment.foodtruck.service;

import com.assignment.foodtruck.calculation.DistanceFunction;
import com.assignment.foodtruck.domain.FoodTruck;
import com.assignment.foodtruck.domain.Pair;
import com.assignment.foodtruck.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodTruckService {

    private int DEFAULT_MAX_RESULTS = 10;
    private InMemoryRepository inMemoryRepository;

    public FoodTruckService(InMemoryRepository inMemoryRepository) {
        this.inMemoryRepository = inMemoryRepository;
    }

    public List<FoodTruck> findClosestFoodTrucksToPoint(Double latitude, Double longitude, Integer max) throws IllegalArgumentException {
        // Check coordinates
        if (latitude == null || -90 > latitude || latitude > 90)
            throw new IllegalArgumentException("Latitude Value is invalid!!");
        if (longitude == null || -180 > longitude || longitude > 180)
            throw new IllegalArgumentException("Longitude Value is invalid!!");
        if (max == null) max = DEFAULT_MAX_RESULTS;

        List<FoodTruck> originalFoodTrucks = this.inMemoryRepository.getFoodTrucks();

        // Limit Check for max value to original list size
        if (max > originalFoodTrucks.size()){
            max = originalFoodTrucks.size();
        }

        // Map all and calculate the distance between two points
        List<Pair> result = originalFoodTrucks.stream()
                .map(foodTruck -> {
                    double resultInKm = DistanceFunction.distanceInKM(latitude, foodTruck.getLatitude(), longitude, foodTruck.getLongitude());
                    return new Pair(resultInKm, foodTruck);
                }).collect(Collectors.toList());

        // Sort the list by distance to point
        result.sort(Comparator.comparing(Pair::getDistanceInKM));

        // Retrieve the top closest food trucks
        List<Pair> subList = result.subList(0, max);

        return subList.stream().map(p -> p.getFoodTruck()).collect(Collectors.toList());

    }

    public List<FoodTruck> getFoodTrucks() {
        return this.inMemoryRepository.getFoodTrucks();
    }
}
