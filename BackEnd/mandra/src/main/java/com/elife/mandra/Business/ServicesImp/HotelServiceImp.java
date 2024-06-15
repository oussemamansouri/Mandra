package com.elife.mandra.Business.ServicesImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImp.class);

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

    // ---------------------------------- add Hotel by owner -----------------------------------

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
                    hotelForm.getNbOfStars());

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



    // ---------------------------------- update Hotel  -----------------------------------

    @Override
    public Hotel updateHotel(Long hotelId, HotelForm hotelForm) {
        try {
            Hotel hotel = hotelRepository.findById(hotelId)
                    .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));

            hotel.setName(hotelForm.getName());
            hotel.setDescription(hotelForm.getDescription());
            hotel.setAddress(hotelForm.getAddress());
            hotel.setCity(hotelForm.getCity());
            hotel.setEmail(hotelForm.getEmail());
            hotel.setFacebook(hotelForm.getFacebook());
            hotel.setInstagram(hotelForm.getInstagram());
            hotel.setHasGym(hotelForm.isHasGym());
            hotel.setHasParking(hotelForm.isHasParking());
            hotel.setHasPool(hotelForm.isHasPool());
            hotel.setHasRestaurant(hotelForm.isHasRestaurant());
            hotel.setHasWifi(hotelForm.isHasWifi());
            hotel.setAllowsPets(hotelForm.isAllowsPets());
            hotel.setNbOfStars(hotelForm.getNbOfStars());
            hotel.setNumberOfRooms(hotelForm.getNumberOfRooms());
            hotel.setPhoneNumber(hotelForm.getPhoneNumber());
            hotel.setWebsite(hotelForm.getWebsite());

            return hotelRepository.save(hotel);
        } catch (Exception e) {
            LOGGER.error("Error while updating hotel", e);
            throw new RuntimeException("Error while updating hotel: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- update Hotel images  -----------------------------------

    @Override
    public Hotel updateHotelImages(Long hotelId, List<MultipartFile> images) {
        try{

        Hotel hotel = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));

        // Save images and associate with the hotel
        List<HotelImage> hotelImages = new ArrayList<>();
        for (MultipartFile image : images) {
            String imagePath = fileService.saveImage(image);
            HotelImage hotelImage = new HotelImage();
            hotelImage.setImagePath(imagePath);
            hotelImage.setHotel(hotel);
            hotelImages.add(hotelImage);
        }

        hotelImageRepository.saveAll(hotelImages);
        
        return hotelRepository.save(hotel);
        }catch(Exception e){
            LOGGER.error("Error while updating hotel images", e);
            throw new RuntimeException("Error while updating hotel images: " + e.getMessage(), e);
        }
    }



    // ----------------------------------      get hotels     -----------------------------------

    @Override
    public Page<Hotel> getHotels(Pageable pageable) {
           try {
            return hotelRepository.findAll(pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding hotels", e);
            throw new RuntimeException("Failed to find hotels: " + e.getMessage(), e);
        }
    }




    // ----------------------------------      get hotel by id    -----------------------------------

    @Override
    public Hotel getHotelById(Long hotelId) {
        try {
            return hotelRepository.findById(hotelId).get();
        } catch (Exception e) {
            LOGGER.error("Error while finding hotel with this id :" + hotelId +" :", e);
            throw new RuntimeException("Failed to find hotel with this id "+ hotelId +" : " + e.getMessage(), e);
        }
    }

}
