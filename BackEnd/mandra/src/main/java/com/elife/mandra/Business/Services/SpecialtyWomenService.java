package com.elife.mandra.Business.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public SpecialtyWomen updateSpecialtyWomenImage(Long specialtyWomenId,
    MultipartFile image);

     // Read operatoin
    public Page<SpecialtyWomen> getSpecialtyWomens(Pageable pageable);
    public SpecialtyWomen getSpecialtyWomenById(Long specialtyWomenId);


}
