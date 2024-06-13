package com.elife.mandra.Business.ServicesImp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.HotelService;
import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Repositories.HotelRepository;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;

@Service
public class HotelServiceImp implements HotelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImp.class);

    final HotelRepository hotelRepository;
    final OwnerRepository ownerRepository;

    public HotelServiceImp(HotelRepository hotelRepository, OwnerRepository ownerRepository) {
        this.hotelRepository = hotelRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Hotel addHotel(Long ownerId, HotelForm hotelForm) {
        try {
            Owner owner = ownerRepository.findById(ownerId).get();
            int nbOfHotelsAllows = owner.getNbOfHotels();
            int nbOfHotelsPosted = owner.getHotels().size();
            if (nbOfHotelsPosted <= nbOfHotelsAllows) {
                Hotel newHotel = new Hotel(hotelForm.getName(),
                        hotelForm.getDescription(),
                        hotelForm.getEmail(),
                        hotelForm.getAddress(),
                        hotelForm.getCity(),
                        hotelForm.getPhoneNumber(),
                        hotelForm.getWebsite(),
                        hotelForm.isHasParking(),
                        hotelForm.isHasWifi(),
                        hotelForm.isAllowsPets(),
                        hotelForm.getFacebook(),
                        hotelForm.getInstagram(),
                        hotelForm.getNumberOfRooms(),
                        hotelForm.isHasGym(),
                        hotelForm.isHasPool(),
                        hotelForm.isHasRestaurant(),
                        hotelForm.getNbOfStars());
                return hotelRepository.save(newHotel);
            } else {
                throw new RuntimeException("You passed the total number of posting hotels allows!");
            }
        } catch (Exception e) {
            LOGGER.error("Error while adding hotel", e);
            throw new RuntimeException("Error while adding hotel: " + e.getMessage(), e);
        }
    }

}
