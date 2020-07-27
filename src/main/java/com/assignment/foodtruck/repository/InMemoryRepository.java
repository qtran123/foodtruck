package com.assignment.foodtruck.repository;

import com.assignment.foodtruck.domain.FoodTruck;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository {
    private String datasetLocation;
    private List<FoodTruck> foodTrucks;

    public InMemoryRepository(String datasetLocation) throws Exception {
        // Load DataSets from classpath
        this.datasetLocation = datasetLocation;
        foodTrucks = new ArrayList<>();
        processDataSet();

    }

    public List<FoodTruck> getFoodTrucks() {
        return this.foodTrucks;
    }

    private void processDataSet() throws Exception {
        File resource = new ClassPathResource(this.datasetLocation).getFile();
        List<String> allLines = Files.readAllLines(resource.toPath());
        // Skip header
        for (int i = 1; i < allLines.size(); i++) {
            String line = allLines.get(i);

            // Ignore commas inside quotes, deal with empty columns
            String[] values = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
            foodTrucks.add(parseFoodTruckData(values));

        }

    }

    private FoodTruck parseFoodTruckData(String[] values) {
        return FoodTruck.builder()
                .locationId(Long.valueOf(values[0]))
                .applicant(values[1])
                .facilityType(values[2])
                .cnn(values[3])
                .locationDescription(values[4])
                .address(values[5])
                .blocklot(values[6])
                .block(values[7])
                .lot(values[8])
                .permit(values[9])
                .Status(values[10])
                .foodItems(values[11])
                .x(values[12])
                .y(values[13])
                .latitude(Double.valueOf(values[14]))
                .longitude(Double.valueOf(values[15]))
                .schedule(values[16])
                .dayshours(values[17])
                .noiSent(values[18])
                .approved(values[19])
                .received(values[20])
                .priorPermit(values[21])
                .expirationDate(values[22])
                .location(values[23])
                .firePreventionDistricts(values[24])
                .policeDistricts(values[25])
                .supervisorDistricts(values[26])
                .zipCodes(values[27])
                .neighborhoods(values[28]).build();
    }


}
