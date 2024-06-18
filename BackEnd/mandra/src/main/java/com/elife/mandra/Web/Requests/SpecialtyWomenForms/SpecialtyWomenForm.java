package com.elife.mandra.Web.Requests.SpecialtyWomenForms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SpecialtyWomenForm {

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

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Size(min = 8, max = 15, message = "Le numéro de téléphone doit comporter entre 8 et 15 caractères")
    @Pattern(regexp = "\\d{8,15}", message = "Le numéro de téléphone doit comporter entre 8 et 15 chiffres")
    private String phoneNumber;

}
