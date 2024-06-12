package com.elife.mandra.Business.Services;

import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;

public interface HotelService {

    //Create operation
    public Hotel addHotel(Long id, HotelForm hotel);


}
