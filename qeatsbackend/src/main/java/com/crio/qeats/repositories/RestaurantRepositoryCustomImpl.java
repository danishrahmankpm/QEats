package com.crio.qeats.repositories;

import com.crio.qeats.models.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public class RestaurantRepositoryCustomImpl implements RestaurantRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    

    @Override
    public List<RestaurantEntity> findAll() {

               
        
                // Execute query with MongoTemplate
                return mongoTemplate.findAll(RestaurantEntity.class);
       
     
    }
}

