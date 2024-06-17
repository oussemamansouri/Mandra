package com.elife.mandra.Business.ServicesImp;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.GastronomicSpecialtiesService;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.DAO.Entities.GastronomicSpecialties;
import com.elife.mandra.DAO.Repositories.AdminRepository;
import com.elife.mandra.DAO.Repositories.GastronomicRepository;
import com.elife.mandra.Web.Requests.GastronomicSpecialtiesForm;

@Service
public class GastronomicSpecialtiesServiceImp implements GastronomicSpecialtiesService {


     private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImp.class);

    final GastronomicRepository gastronomicRepository;
    final AdminRepository adminRepository;
    final FileService fileService;

    private GastronomicSpecialtiesServiceImp(GastronomicRepository gastronomicRepository,
    AdminRepository adminRepository, FileService fileService){

        this.gastronomicRepository = gastronomicRepository;
        this.adminRepository = adminRepository;
        this.fileService = fileService;
    }




    // ---------------------------------- add Gastronomic Specialties by admin -----------------------------------

    @Override
    public GastronomicSpecialties addGastronomicSpecialties(Long adminId,
            GastronomicSpecialtiesForm gastronomicSpecialtiesForm , MultipartFile image) {

        try {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("Admin not found with id: " + adminId));
    
            if (image == null) {
                throw new RuntimeException("Please select one image to upload.");
            }

             // Validate if the file is an image
             String extension = FilenameUtils.getExtension(image.getOriginalFilename());
             if (!extension.matches("jpg|jpeg|png|gif")) {
                 throw new RuntimeException("Invalid image file type");
            }

            String imagePath = fileService.saveFile(image);
    
            GastronomicSpecialties newgastronomicSpecialties = new GastronomicSpecialties(
                gastronomicSpecialtiesForm.getName(),
                gastronomicSpecialtiesForm.getDescription(),
                gastronomicSpecialtiesForm.getAddress(),
                gastronomicSpecialtiesForm.getCity(),
                imagePath
            );
    
            newgastronomicSpecialties.setAdmin(admin);

            return gastronomicRepository.save(newgastronomicSpecialties);

        } catch (Exception e) {
            LOGGER.error("Error while adding gastronomic specialties", e);
            throw new RuntimeException("Error while adding gastronomic specialties: " + e.getMessage(), e);
        }
    }

}
