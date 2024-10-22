package com.elife.mandra.Business.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Restaurant;
import com.elife.mandra.Web.Requests.PropertyForms.RestaurantForm;

public interface RestaurantService {

    //Create operation
    public Restaurant addReataurant(Long ownerId, RestaurantForm restaurantForm, List<MultipartFile> images);

    //Update operation
    public Restaurant updateRestaurant(Long restaurantId, RestaurantForm restaurantForm);
    public Restaurant updateRestaurantImage(Long restaurantId, List<MultipartFile> images);

    //Read operation
    public Page<Restaurant> getRestaurants(String searchTerm, Pageable pageable);
    public Restaurant getRestaurantById(Long restaurantId);

    //Delete operation
    public String deleteRestaurant(Long restaurantId);
}
