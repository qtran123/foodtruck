package com.assignment.foodtruck.config;

import com.assignment.foodtruck.repository.InMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Value("${foodTruckDataSet}")
    private String datasetLocation;

    @Bean
    public InMemoryRepository getInMemoryRepository() throws Exception {
        return new InMemoryRepository(datasetLocation);
    }
}
