package com.elife.mandra.Business.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Guesthouse;
import com.elife.mandra.Web.Requests.PropertyForms.GuesthouseForm;

public interface GuesthouseService {

    // Create operation
    public Guesthouse addGuestHouse(Long ownerId, GuesthouseForm guesthouseForm, List<MultipartFile> images);

    //Update operation
    public Guesthouse updateGuestHouse(Long guestHouseId, GuesthouseForm guesthouseForm);
}
