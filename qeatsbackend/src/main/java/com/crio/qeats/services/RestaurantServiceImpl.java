
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import com.crio.qeats.repositoryservices.RestaurantRepositoryServiceDummyImpl;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
      List<Restaurant> restaurants=new ArrayList<>(); 
      Double servingRadius=getRadius(currentTime);
      restaurants=restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime,servingRadius);
      GetRestaurantsResponse restaurantResponse=new GetRestaurantsResponse(restaurants);
      return restaurantResponse;
  }

  public Double getRadius(LocalTime currentTime) {
  
    LocalTime morningStart = LocalTime.of(8, 0);
    LocalTime morningEnd = LocalTime.of(10, 0);
  
    LocalTime afternoonStart = LocalTime.of(13, 0); 
    LocalTime afternoonEnd = LocalTime.of(14, 0);   
  
    LocalTime eveningStart = LocalTime.of(19, 0);   
    LocalTime eveningEnd = LocalTime.of(21, 0);     
  
    if ((!currentTime.isBefore(morningStart) && !currentTime.isAfter(morningEnd)) ||
        (!currentTime.isBefore(afternoonStart) && !currentTime.isAfter(afternoonEnd)) ||
        (!currentTime.isBefore(eveningStart) && !currentTime.isAfter(eveningEnd))) {
      return peakHoursServingRadiusInKms;
    } else {
      return normalHoursServingRadiusInKms;
    }
  }
  
  
  

}

