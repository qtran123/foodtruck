package com.assignment.foodtruck.controller;

import com.assignment.foodtruck.domain.FoodTruck;
import com.assignment.foodtruck.service.FoodTruckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodTruckController {

    private FoodTruckService foodTruckService;

    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GetMapping("/")
    public List<FoodTruck> getFoodTrucks() {
        return foodTruckService.getFoodTrucks();
    }

    @GetMapping("/foodtrucks")
    public List<FoodTruck> getFoodTrucksByCoordinates(@RequestParam(name = "latitude") Double latitude,
                                                      @RequestParam(name = "longitude") Double longitude,
                                                      @RequestParam(name = "max", required = false) Integer max) {
        return foodTruckService.findClosestFoodTrucksToPoint(latitude, longitude, max);
    }

}
