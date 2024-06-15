package com.elife.mandra.Business.ServicesImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.HotelService;
import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.DAO.Entities.HotelImage;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Repositories.HotelImageRepository;
import com.elife.mandra.DAO.Repositories.HotelRepository;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;

@Service
public class HotelServiceImp implements HotelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImp.class);

    final HotelRepository hotelRepository;
    final OwnerRepository ownerRepository;
    final HotelImageRepository hotelImageRepository;
    final FileService fileService;

    public HotelServiceImp(HotelRepository hotelRepository, OwnerRepository ownerRepository,
            HotelImageRepository hotelImageRepository, FileService fileService) {

        this.hotelRepository = hotelRepository;
        this.ownerRepository = ownerRepository;
        this.hotelImageRepository = hotelImageRepository;
        this.fileService = fileService;
    }

    @Override
    public Hotel addHotel(Long ownerId, HotelForm hotelForm, List<MultipartFile> images) {
        try {
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));
    
            int nbOfHotelsAllows = owner.getNbOfHotels();
            int nbOfHotelsPosted = owner.getHotels().size();
    
            if (nbOfHotelsPosted >= nbOfHotelsAllows) {
                throw new RuntimeException("You have exceeded the number of hotels you can post!");
            }
    
            Hotel newHotel = new Hotel(
                hotelForm.getName(),
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
                hotelForm.getNbOfStars()
        );

        newHotel.setOwner(owner);

        // Save hotel to get the ID
        Hotel savedHotel = hotelRepository.save(newHotel);

        // Save images and associate with the hotel
        List<HotelImage> hotelImages = new ArrayList<>();
        for (MultipartFile image : images) {
            String imagePath = fileService.saveImage(image);
            HotelImage hotelImage = new HotelImage();
            hotelImage.setImagePath(imagePath);
            hotelImage.setHotel(savedHotel);
            hotelImages.add(hotelImage);
        }

        hotelImageRepository.saveAll(hotelImages);

        // Associate images with the hotel
        savedHotel.setHotelImage(hotelImages);

        return hotelRepository.save(savedHotel);
    } catch (Exception e) {
        LOGGER.error("Error while adding hotel", e);
        throw new RuntimeException("Error while adding hotel: " + e.getMessage(), e);
    }
}
    

}
