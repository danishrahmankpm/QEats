
package com.crio.qeats.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import com.crio.qeats.utils.FixtureHelpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceMockitoTestStub {

  protected static final String FIXTURES = "fixtures/exchanges";

  protected ObjectMapper objectMapper = new ObjectMapper();

  protected Restaurant restaurant1;
  protected Restaurant restaurant2;
  protected Restaurant restaurant3;
  protected Restaurant restaurant4;
  protected Restaurant restaurant5;

  @InjectMocks
  protected RestaurantServiceImpl restaurantService;

  @Mock
  protected RestaurantRepositoryService restaurantRepositoryServiceMock;


  public void initializeRestaurantObjects() throws IOException {
    String fixture = FixtureHelpers.fixture(FIXTURES + "/mocking_list_of_restaurants.json");
    Restaurant[] restaurants = objectMapper.readValue(fixture, Restaurant[].class);
    restaurant1 = restaurants[0];
    restaurant2 = restaurants[1];
  }

  @BeforeEach
  public void setUp() throws IOException {
    initializeRestaurantObjects();
  }



  @Test
  public void  testFindNearbyWithin5km() throws IOException {
    //TODO: CRIO_TASK_MODULE_MOCKITO
    // Following test case is failing, you have to
    // debug it, find out whats going wrong and fix it.
    // Notes - You can create additional mocks, setup the same and try out.


    when(restaurantRepositoryServiceMock.
        findAllRestaurantsCloseBy(any(Double.class), any(Double.class),
        any(LocalTime.class), any(Double.class))).
        thenReturn(Arrays.asList(restaurant1, restaurant2));
    GetRestaurantsResponse allRestaurantsCloseBy = restaurantService
        .findAllRestaurantsCloseBy(new GetRestaurantsRequest(20.0, 30.0),
            LocalTime.of(3, 0));

    assertEquals(2, allRestaurantsCloseBy.getRestaurants().size());
    assertEquals("11", allRestaurantsCloseBy.getRestaurants().get(0).getRestaurantId());
    assertEquals("12", allRestaurantsCloseBy.getRestaurants().get(1).getRestaurantId());

    ArgumentCaptor<Double> radiusCaptor = ArgumentCaptor.forClass(Double.class);
    verify(restaurantRepositoryServiceMock, times(1))
        .findAllRestaurantsCloseBy(any(Double.class), any(Double.class), any(LocalTime.class),
        radiusCaptor.capture());

        assertEquals(5.0, radiusCaptor.getValue(), 0.001);


  }


  @Test
  public void  testFindNearbyWithin3km() throws IOException {
    Restaurant r11 = new Restaurant();
    r11.setRestaurantId("11");
  
    Restaurant r12 = new Restaurant();
    r12.setRestaurantId("12");
  
    Restaurant r14 = new Restaurant();
    r14.setRestaurantId("14");

    List<Restaurant> restaurantList1 = Arrays.asList(r11, r14); // Off-peak mock return
    List<Restaurant> restaurantList2 = Arrays.asList(r12, r14); // Peak mock return
  

    // TODO: CRIO_TASK_MODULE_MOCKITO
    //  Initialize these two lists above such that I will match with the assert statements
    //  defined below.


    lenient().doReturn(restaurantList1)
        .when(restaurantRepositoryServiceMock)
        .findAllRestaurantsCloseBy(eq(20.0), eq(30.2), eq(LocalTime.of(3, 0)),
            eq(5.0));

    lenient().doReturn(restaurantList2)
        .when(restaurantRepositoryServiceMock)
        .findAllRestaurantsCloseBy(eq(21.0), eq(31.1), eq(LocalTime.of(19, 0)),
            eq(3.0));

    // Call the service method with correct params to hit the mocks
    GetRestaurantsResponse allRestaurantsCloseByOffPeakHours = restaurantService
        .findAllRestaurantsCloseBy(new GetRestaurantsRequest(20.0, 30.2), LocalTime.of(3, 0));

    GetRestaurantsResponse allRestaurantsCloseByPeakHours = restaurantService
        .findAllRestaurantsCloseBy(new GetRestaurantsRequest(21.0, 31.1), LocalTime.of(19, 0));

    // TODO: CRIO_TASK_MODULE_MOCKITO
    //  Call restaurantService.findAllRestaurantsCloseBy with appropriate parameters such that
    //  Both of the mocks created above are called.
    //  Our assessment will verify whether these mocks are called as per the definition.
    //  Refer to the assertions below in order to understand the requirements better.


    assertEquals(2, allRestaurantsCloseByOffPeakHours.getRestaurants().size());
    assertEquals("11", allRestaurantsCloseByOffPeakHours.getRestaurants().get(0).getRestaurantId());
    assertEquals("14", allRestaurantsCloseByOffPeakHours.getRestaurants().get(1).getRestaurantId());


    assertEquals(2, allRestaurantsCloseByPeakHours.getRestaurants().size());
    assertEquals("12", allRestaurantsCloseByPeakHours.getRestaurants().get(0).getRestaurantId());
    assertEquals("14", allRestaurantsCloseByPeakHours.getRestaurants().get(1).getRestaurantId());
  }

}

