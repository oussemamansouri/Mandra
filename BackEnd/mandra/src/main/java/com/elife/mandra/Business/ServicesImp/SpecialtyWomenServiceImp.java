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
                specialtyWomenForm.getName(),
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

}
