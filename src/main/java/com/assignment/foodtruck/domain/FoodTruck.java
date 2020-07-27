package com.assignment.foodtruck.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodTruck{
    private Long locationId;
    private String applicant;
    private String facilityType;
    private String cnn;
    private String locationDescription;
    private String address;
    private String blocklot;
    private String block;
    private String lot;
    private String permit;
    private String Status;
    private String foodItems;
    private String x;
    private String y;
    private Double latitude;
    private Double longitude;
    private String schedule;
    private String dayshours;
    private String noiSent;
    private String approved;
    private String received;
    private String priorPermit;
    private String expirationDate;
    private String location;
    private String firePreventionDistricts;
    private String policeDistricts;
    private String supervisorDistricts;
    private String zipCodes;
    private String neighborhoods;
}