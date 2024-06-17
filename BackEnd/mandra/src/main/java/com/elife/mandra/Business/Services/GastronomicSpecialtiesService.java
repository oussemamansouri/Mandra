package com.elife.mandra.Business.Services;

import com.elife.mandra.DAO.Entities.GastronomicSpecialties;
import com.elife.mandra.Web.Requests.GastronomicSpecialtiesForm;

public interface GastronomicSpecialtiesService {

    // Create operation
    public GastronomicSpecialties addGastronomicSpecialties(Long adminId, GastronomicSpecialtiesForm gastronomicSpecialtiesForm);

}
