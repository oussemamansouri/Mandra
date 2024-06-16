package com.elife.mandra.Business.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Guesthouse;
import com.elife.mandra.Web.Requests.PropertyForms.GuesthouseForm;

public interface GuesthouseService {

    // Create operation
    public Guesthouse addGuestHouse(Long ownerId, GuesthouseForm guesthouseForm, List<MultipartFile> images);

    //Update operation
    public Guesthouse updateGuestHouse(Long guestHouseId, GuesthouseForm guesthouseForm);
    public Guesthouse updateGuestHouseImage(Long guestHouseId, List<MultipartFile> images);

     //Read operation
    public Page<Guesthouse> getGuestHouses(Pageable pageable);
    public Guesthouse getGuestHousesById(Long guestHouseId);


}
