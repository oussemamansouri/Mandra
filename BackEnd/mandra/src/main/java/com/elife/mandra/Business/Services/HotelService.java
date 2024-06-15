package com.elife.mandra.Business.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;


public interface HotelService {

    //Create operation
    public Hotel addHotel(Long ownerId, HotelForm hotel, List<MultipartFile> images);

    //Update operation
    public Hotel updateHotel(Long hotelId, HotelForm hotelForm);
    public Hotel updateHotelImages(Long hotelId, List<MultipartFile> images);

    //Read operation
    public Page<Hotel> getHotels(Pageable pageable);
    public Hotel getHotelById(Long hotelId);

    //Delete operation
    public String deleteHotel(Long hotelId);

}
