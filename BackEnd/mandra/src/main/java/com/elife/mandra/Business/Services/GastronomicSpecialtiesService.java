package com.elife.mandra.Business.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.GastronomicSpecialties;
import com.elife.mandra.Web.Requests.GastromnmicSpecialtiesForms.GastronomicSpecialtiesForm;

public interface GastronomicSpecialtiesService {

    // Create operation
    public GastronomicSpecialties addGastronomicSpecialtie(Long adminId,
            GastronomicSpecialtiesForm gastronomicSpecialtiesForm,
            MultipartFile image);

    // Update operation
    public GastronomicSpecialties updateGastronomicSpecialtie(Long GastronomicSpecialtieId,
            GastronomicSpecialtiesForm gastronomicSpecialtiesForm);

    public GastronomicSpecialties updateGastronomicSpecialtieImage(Long GastronomicSpecialtieId, MultipartFile image);

    // Read operatoin
    public Page<GastronomicSpecialties> getGastronomicSpecialties(Pageable pageable);
    public GastronomicSpecialties getGastronomicSpecialtieById(Long GastronomicSpecialtieId);

    //delete operation
    public String deleteGastronomicSpecialtie(Long GastronomicSpecialtieId);

}
