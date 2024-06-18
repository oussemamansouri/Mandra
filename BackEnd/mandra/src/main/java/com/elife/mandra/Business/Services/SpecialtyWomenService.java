package com.elife.mandra.Business.Services;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.SpecialtyWomen;
import com.elife.mandra.Web.Requests.SpecialtyWomenForms.SpecialtyWomenForm;

public interface SpecialtyWomenService {

    // Create Operation
    public SpecialtyWomen addSpecialtyWomenForm(Long adminId, SpecialtyWomenForm specialtyWomenForm,
            MultipartFile image);

    // Update operation
    public SpecialtyWomen updateSpecialtyWomen(Long specialtyWomenId,
    SpecialtyWomenForm specialtyWomenForm);

}
