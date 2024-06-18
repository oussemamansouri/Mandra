package com.elife.mandra.Business.ServicesImp;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.SpecialtyWomenService;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.DAO.Entities.SpecialtyWomen;
import com.elife.mandra.DAO.Repositories.AdminRepository;
import com.elife.mandra.DAO.Repositories.SpecialtyWomenRepository;
import com.elife.mandra.Web.Requests.SpecialtyWomenForms.SpecialtyWomenForm;


@Service
public class SpecialtyWomenServiceImp implements SpecialtyWomenService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialtyWomenServiceImp.class);

    final SpecialtyWomenRepository specialtyWomenRepository;
    final AdminRepository adminRepository;
    final FileService fileService;

    private SpecialtyWomenServiceImp(SpecialtyWomenRepository specialtyWomenRepository,
    AdminRepository adminRepository, FileService fileService){

        this.specialtyWomenRepository = specialtyWomenRepository;
        this.adminRepository = adminRepository;
        this.fileService = fileService;
    }




    // ---------------------------------- add Specialty Women by admin -----------------------------------

    @Override
    public SpecialtyWomen addSpecialtyWomenForm(Long adminId, SpecialtyWomenForm specialtyWomenForm,
            MultipartFile image) {

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
    
            SpecialtyWomen newSpecialtyWomen = new SpecialtyWomen(
                specialtyWomenForm.getSpecialty(),
                specialtyWomenForm.getDescription(),
                specialtyWomenForm.getAddress(),
                specialtyWomenForm.getCity(),
                specialtyWomenForm.getPhoneNumber(),
                imagePath
            );
    
            newSpecialtyWomen.setAdmin(admin);

            return specialtyWomenRepository.save(newSpecialtyWomen);

        } catch (Exception e) {
            LOGGER.error("Error while adding specialty women", e);
            throw new RuntimeException("Error while adding specialty women: " + e.getMessage(), e);
        }
    }






    // ---------------------------------- update Specialty Women -----------------------------------

    @Override
    public SpecialtyWomen updateSpecialtyWomen(Long specialtyWomenId, SpecialtyWomenForm specialtyWomenForm) {
       try {
        SpecialtyWomen specialtyWomen = specialtyWomenRepository.findById(specialtyWomenId)
                    .orElseThrow(() -> new RuntimeException("specialty women not found with id: " + specialtyWomenId));

                    specialtyWomen.setSpecialty(specialtyWomenForm.getSpecialty());
                    specialtyWomen.setDescription(specialtyWomenForm.getDescription());
                    specialtyWomen.setAddress(specialtyWomenForm.getAddress());
                    specialtyWomen.setCity(specialtyWomenForm.getCity());
                    specialtyWomen.setPhoneNumber(specialtyWomenForm.getPhoneNumber());

            return specialtyWomenRepository.save(specialtyWomen);
        } catch (Exception e) {
            LOGGER.error("Error while updating specialty women", e);
            throw new RuntimeException("Error while updating specialty women: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- update Specialty Women Image -----------------------------------

    @Override
    public SpecialtyWomen updateSpecialtyWomenImage(Long specialtyWomenId, MultipartFile image) {
         try {

            // Fetch the specialty women first
            SpecialtyWomen specialtyWomen = specialtyWomenRepository.findById(specialtyWomenId).orElse(null);
            if (specialtyWomen == null) {
                throw new RuntimeException("Failed to find specialty women with this id: " + specialtyWomenId);
            }

            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image file type");
            }
    
            String imagePath = fileService.saveFile(image); // Save the image and get the path
            specialtyWomen.setImage(imagePath);
            return specialtyWomenRepository.save(specialtyWomen);
        } catch (Exception e) {
            LOGGER.error("Error while updating specialty women image", e);
            throw new RuntimeException("Error while updating specialty women image: " + e.getMessage(), e);
        }
    }

}
