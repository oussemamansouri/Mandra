package com.elife.mandra.Business.ServicesImp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.StatisticsService;
import com.elife.mandra.DAO.Repositories.ClientRepository;
import com.elife.mandra.DAO.Repositories.ContactRepository;
import com.elife.mandra.DAO.Repositories.GastronomicRepository;
import com.elife.mandra.DAO.Repositories.GuestHouseRepository;
import com.elife.mandra.DAO.Repositories.HotelRepository;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.DAO.Repositories.RestaurantRepository;
import com.elife.mandra.DAO.Repositories.SpecialtyWomenRepository;

@Service
public class StatisticsServiceImp implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImp.class);

    final ClientRepository clientRepository;
    final OwnerRepository ownerRepository;
    final HotelRepository hotelRepository;
    final RestaurantRepository restaurantRepository;
    final GastronomicRepository gastronomicRepository;
    final SpecialtyWomenRepository specialtyWomenRepository;
    final GuestHouseRepository guestHouseRepository;
    final ContactRepository contactRepository;

    public StatisticsServiceImp(ClientRepository clientRepository,
            OwnerRepository ownerRepository, HotelRepository hotelRepository, RestaurantRepository restaurantRepository,
            GastronomicRepository gastronomicRepository, SpecialtyWomenRepository specialtyWomenRepository,
            GuestHouseRepository guestHouseRepository,
            ContactRepository contactRepository) {

        this.clientRepository = clientRepository;
        this.ownerRepository = ownerRepository;
        this.hotelRepository = hotelRepository;
        this.restaurantRepository = restaurantRepository;
        this.gastronomicRepository = gastronomicRepository;
        this.specialtyWomenRepository = specialtyWomenRepository;
        this.guestHouseRepository = guestHouseRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public Map<String, Long> getCounts() {
        Map<String, Long> counts = new HashMap<>();
        try {
            counts.put("Clients", clientRepository.count());
            counts.put("Owners", ownerRepository.count());
            counts.put("Hotels", hotelRepository.count());
            counts.put("Restaurants", restaurantRepository.count());
            counts.put("GuestHouses", guestHouseRepository.count());
            counts.put("Gastronomics", gastronomicRepository.count());
            counts.put("SpecialtyWomens", specialtyWomenRepository.count());
            counts.put("Contacts", contactRepository.count());
            return counts;
        } catch (Exception e) {
            LOGGER.error("Error while counting the content of all entities", e);
            throw new RuntimeException("Error while counting the content of all entities: " + e.getMessage(), e);
        }
    }

}
