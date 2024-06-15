package com.elife.mandra.Business.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Restaurant;
import com.elife.mandra.Web.Requests.PropertyForms.RestaurantForm;

public interface RestaurantService {

    //Create operation
    public Restaurant addReataurant(Long ownerId, RestaurantForm restaurantForm, List<MultipartFile> images);

    //Update operation
    public Restaurant updateRestaurant(Long restaurantId, RestaurantForm restaurantForm);
}
