package com.elife.mandra.Web.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class GastronomicSpecialtiesForm {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 40, message = "Le nom doit comporter moins de 40 caractères")
    private String name;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 100, message = "L'adresse doit comporter moins de 100 caractères")
    private String address;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 30, message = "La ville doit comporter moins de 30 caractères")
    private String city;

}
