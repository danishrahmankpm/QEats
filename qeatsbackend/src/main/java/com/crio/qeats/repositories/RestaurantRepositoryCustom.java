package com.crio.qeats.repositories;

import com.crio.qeats.models.RestaurantEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantRepositoryCustom {
    List<RestaurantEntity> findAll(
    
    );
}
