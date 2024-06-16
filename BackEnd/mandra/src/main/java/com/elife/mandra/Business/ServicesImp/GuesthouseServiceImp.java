package com.elife.mandra.Business.ServicesImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.GuesthouseService;
import com.elife.mandra.DAO.Entities.Guesthouse;
import com.elife.mandra.DAO.Entities.GuesthouseImage;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Repositories.GuestHouseRepository;
import com.elife.mandra.DAO.Repositories.GuesthouseImageRepository;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.PropertyForms.GuesthouseForm;

@Service
public class GuesthouseServiceImp implements GuesthouseService{


     private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImp.class);

    final GuestHouseRepository guestHouseRepository;
    final OwnerRepository ownerRepository;
    final GuesthouseImageRepository guesthouseImageRepository;
    final FileService fileService;

    private GuesthouseServiceImp(GuestHouseRepository guestHouseRepository,OwnerRepository ownerRepository,
    GuesthouseImageRepository guesthouseImageRepository, FileService fileService){

        this.guestHouseRepository = guestHouseRepository;
        this.guesthouseImageRepository = guesthouseImageRepository;
        this.ownerRepository = ownerRepository;
        this.fileService = fileService;
    }



    // ---------------------------------- add guest house by owner -----------------------------------

    @Override
    public Guesthouse addGuestHouse(Long ownerId, GuesthouseForm guesthouseForm, List<MultipartFile> images) {
         try {
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));
    
            int nbOfGuestHousesAllows = owner.getNbOfGuesthouses();
            int nbOfGuestHousesPosted = owner.getGuesthouses().size();
    
            if (nbOfGuestHousesPosted >= nbOfGuestHousesAllows) {
                throw new RuntimeException("You have exceeded the number of guest houses you can post!");
            }
    
            if (images == null || images.isEmpty()) {
                throw new RuntimeException("Please select at least one image to upload.");
            } else if (images.size() > 5) {
                throw new RuntimeException("You have exceeded the maximum number of images allowed!");
            }
    
            Guesthouse newGuestHouse = new Guesthouse(
                guesthouseForm.getName(),
                guesthouseForm.getDescription(),
                guesthouseForm.getEmail(),
                guesthouseForm.getAddress(),
                guesthouseForm.getCity(),
                guesthouseForm.getPhoneNumber(),
                guesthouseForm.getWebsite(),
                guesthouseForm.isHasParking(),
                guesthouseForm.isHasWifi(),
                guesthouseForm.isAllowsPets(),
                guesthouseForm.getFacebook(),
                guesthouseForm.getInstagram(),
                guesthouseForm.isHasPool(),
                guesthouseForm.isHasRestaurant()
            );
    
            newGuestHouse.setOwner(owner);
    
            // Save guest houses to get the ID
            Guesthouse savedGuestHouse = guestHouseRepository.save(newGuestHouse);
    
            // Save images and associate with the guest houses
            List<GuesthouseImage> guestHouseImages = new ArrayList<>();
            for (MultipartFile image : images) {
                if (image.isEmpty()) {
                    continue; // Skip empty files
                }
    
                String imagePath = fileService.saveImage(image);
                GuesthouseImage guestHouseImage = new GuesthouseImage();
                guestHouseImage.setImagePath(imagePath);
                guestHouseImage.setGuesthouse(savedGuestHouse);
                guestHouseImages.add(guestHouseImage);
            }
    
            if (guestHouseImages.isEmpty()) {
                throw new RuntimeException("No valid images to upload.");
            }
    
            guesthouseImageRepository.saveAll(guestHouseImages);
    
            // Associate images with the guest houses
            savedGuestHouse.setGuestHouseImage(guestHouseImages);
    
            return guestHouseRepository.save(savedGuestHouse);
        } catch (Exception e) {
            LOGGER.error("Error while adding guest houses", e);
            throw new RuntimeException("Error while adding guest houses: " + e.getMessage(), e);
        }
    }

}
