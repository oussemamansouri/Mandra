package com.elife.mandra.Business.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;


public interface HotelService {

    //Create operation
    public Hotel addHotel(Long ownerId, HotelForm hotel, List<MultipartFile> images);

    //update operation
    public Hotel updateHotel(Long hotelId, HotelForm hotelForm);


}
