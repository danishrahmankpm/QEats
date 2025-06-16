/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.exchanges;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.crio.qeats.dto.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
//  Implement GetRestaurantsRequest.
//  Complete the class such that it is able to deserialize the incoming query params from
//  REST API clients.
//  For instance, if a REST client calls API
//  /qeats/v1/restaurants?latitude=28.4900591&longitude=77.536386&searchFor=tamil,
//  this class should be able to deserialize lat/long and optional searchFor from that.
@Data
@NoArgsConstructor
public class GetRestaurantsRequest {
    
    private double latitude;
    private double longitude;
    private String searchFor;
    double servingRadius;
    LocalTime currentTime;

    public GetRestaurantsRequest(Double latitude,Double longitude,LocalTime currentTime){
        this.currentTime=currentTime;
        this.latitude=latitude;
        this.longitude=longitude;    
    }
    public GetRestaurantsRequest(Double latitude,Double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }
}

