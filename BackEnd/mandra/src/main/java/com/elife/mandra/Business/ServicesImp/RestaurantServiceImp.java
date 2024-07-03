package com.elife.mandra.Business.ServicesImp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.RestaurantService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Entities.Restaurant;
import com.elife.mandra.DAO.Entities.RestaurantImage;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.DAO.Repositories.RestaurantImageRepository;
import com.elife.mandra.DAO.Repositories.RestaurantRepository;
import com.elife.mandra.Web.Requests.PropertyForms.RestaurantForm;


@Service
public class RestaurantServiceImp implements RestaurantService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImp.class);

    final RestaurantRepository restaurantRepository;
    final OwnerRepository ownerRepository;
    final RestaurantImageRepository restaurantImageRepository;
    final FileService fileService;

    private RestaurantServiceImp(RestaurantRepository restaurantRepository,OwnerRepository ownerRepository,
    RestaurantImageRepository restaurantImageRepository, FileService fileService){

        this.restaurantRepository = restaurantRepository;
        this.restaurantImageRepository = restaurantImageRepository;
        this.ownerRepository = ownerRepository;
        this.fileService = fileService;
    }


    // ---------------------------------- add Restaurant by owner -----------------------------------

    @Override
    public Restaurant addReataurant(Long ownerId, RestaurantForm restaurantForm, List<MultipartFile> images) {
        try {
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));
    
            int nbOfRestaurantsAllows = owner.getNbOfRestaurant();
            int nbOfRestaurantsPosted = owner.getRestaurants().size();
    
            if (nbOfRestaurantsPosted >= nbOfRestaurantsAllows) {
                throw new RuntimeException("You have exceeded the number of restaurants you can post!");
            }
    
            if (images == null || images.isEmpty()) {
                throw new RuntimeException("Please select at least one image to upload.");
            } else if (images.size() > 5) {
                throw new RuntimeException("You have exceeded the maximum number of images allowed!");
            }
    
            Restaurant newRestaurant = new Restaurant(
                restaurantForm.getName(),
                restaurantForm.getDescription(),
                restaurantForm.getEmail(),
                restaurantForm.getAddress(),
                restaurantForm.getCity(),
                restaurantForm.getPhoneNumber(),
                restaurantForm.getWebsite(),
                restaurantForm.isHasParking(),
                restaurantForm.isHasWifi(),
                restaurantForm.isAllowsPets(),
                restaurantForm.getFacebook(),
                restaurantForm.getInstagram(),
                restaurantForm.isWithTerrace(),
                restaurantForm.isAcceptsReservation()
            );
    
            newRestaurant.setOwner(owner);
    
            // Save restaurant to get the ID
            Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);
    
            // Save images and associate with the restaurant
            List<RestaurantImage> restaurantImages = new ArrayList<>();
            for (MultipartFile image : images) {
                if (image.isEmpty()) {
                    continue; // Skip empty files
                }

                // Validate if the file is an image
                String extension = FilenameUtils.getExtension(image.getOriginalFilename());
                if (!extension.matches("jpg|jpeg|png|gif")) {
                    throw new RuntimeException("Invalid image file type");
                }
    
                String imagePath = fileService.saveFile(image);
                RestaurantImage restaurantImage = new RestaurantImage();
                restaurantImage.setImagePath(imagePath);
                restaurantImage.setRestaurant(savedRestaurant);
                restaurantImages.add(restaurantImage);
            }
    
            if (restaurantImages.isEmpty()) {
                throw new RuntimeException("No valid images to upload.");
            }
    
            restaurantImageRepository.saveAll(restaurantImages);
    
            // Associate images with the restaurant
            savedRestaurant.setRestaurantImage(restaurantImages);
    
            return restaurantRepository.save(savedRestaurant);
        } catch (Exception e) {
            LOGGER.error("Error while adding restaurant", e);
            throw new RuntimeException("Error while adding restaurant: " + e.getMessage(), e);
        }
    }
    




    // ---------------------------------- update Restaurant -----------------------------------

    @Override
    public Restaurant updateRestaurant(Long restaurantId, RestaurantForm restaurantForm) {
       try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));

            restaurant.setName(restaurantForm.getName());
            restaurant.setDescription(restaurantForm.getDescription());
            restaurant.setAddress(restaurantForm.getAddress());
            restaurant.setCity(restaurantForm.getCity());
            restaurant.setEmail(restaurantForm.getEmail());
            restaurant.setFacebook(restaurantForm.getFacebook());
            restaurant.setInstagram(restaurantForm.getInstagram());
            restaurant.setHasParking(restaurantForm.isHasParking());
            restaurant.setHasWifi(restaurantForm.isHasWifi());
            restaurant.setAllowsPets(restaurantForm.isAllowsPets());
            restaurant.setPhoneNumber(restaurantForm.getPhoneNumber());
            restaurant.setWebsite(restaurantForm.getWebsite());
            restaurant.setAcceptsReservation(restaurantForm.isAcceptsReservation());
            restaurant.setWithTerrace(restaurantForm.isWithTerrace());

            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            LOGGER.error("Error while updating restaurant", e);
            throw new RuntimeException("Error while updating restaurant: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- update Restaurant images  -----------------------------------

    @Override
    public Restaurant updateRestaurantImage(Long restaurantId, List<MultipartFile> images) {
        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));
    
            // Check if the number of images is between 1 and 5
            if (images == null || images.isEmpty()) {
                throw new RuntimeException("Please select at least one image to upload.");
            } else if (images.size() > 5) {
                throw new RuntimeException("You have exceeded the maximum number of images allowed!");
            }
    
            // Delete existing images
            List<RestaurantImage> existingImages = restaurant.getRestaurantImage();
            restaurantImageRepository.deleteAll(existingImages);
    
            // Remove the existing images from the restaurant entity
            existingImages.clear();
    
            // Save new images and associate with the restaurant
            List<RestaurantImage> newRestaurantImages = new ArrayList<>();
            for (MultipartFile image : images) {
                if (image.isEmpty()) {
                    continue; // Skip empty files
                }

                // Validate if the file is an image
                String extension = FilenameUtils.getExtension(image.getOriginalFilename());
                if (!extension.matches("jpg|jpeg|png|gif")) {
                    throw new RuntimeException("Invalid image file type");
                }
    
                String imagePath = fileService.saveFile(image);
                RestaurantImage restaurantImage = new RestaurantImage();
                restaurantImage.setImagePath(imagePath);
                restaurantImage.setRestaurant(restaurant);
                newRestaurantImages.add(restaurantImage);
            }
    
            if (newRestaurantImages.isEmpty()) {
                throw new RuntimeException("No valid images to upload.");
            }
    
            // Add new images to the restaurant entity
            existingImages.addAll(newRestaurantImages);
    
            // Save new images
            restaurantImageRepository.saveAll(newRestaurantImages);
    
            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            LOGGER.error("Error while updating restaurant images", e);
            throw new RuntimeException("Error while updating restaurant images: " + e.getMessage(), e);
        }
    }
    
    




    // ---------------------------------- get restaurants -----------------------------------

    @Override
    public Page<Restaurant> getRestaurants(String searchTerm, Pageable pageable) {
        try {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return restaurantRepository.findAll(pageable);
            } else {
                return restaurantRepository.searchRestaurants(searchTerm, pageable);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find restaurants: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- get restaurant by id -----------------------------------

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
      try{
        return restaurantRepository.findById(restaurantId).get();
      }catch(Exception e){
        LOGGER.error("Error while Getting restaurant with this id : "+ restaurantId+" :", e);
        throw new RuntimeException("Failed to find restaurant with this id " + restaurantId + " : " + e.getMessage(), e);
      }
    }




    // ---------------------------------- delete restaurant by id ----------------------------------

    @Override
    public String deleteRestaurant(Long restaurantId) {
        try{
            if (!restaurantRepository.existsById(restaurantId)) {
                throw new RuntimeException("Restaurant not found with id: " + restaurantId);
            }
            restaurantRepository.deleteById(restaurantId);
            return "Restaurant deleted successfully";
        }catch(Exception e){
            LOGGER.error("Error while deleting restaurant with id " + restaurantId, e);
            throw new RuntimeException("Error while deleting restaurant with id " + restaurantId + ": " + e.getMessage(), e);
        }
    }


    

}
