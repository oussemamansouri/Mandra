package com.elife.mandra.Web.Requests.ContactForms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ContactForm {

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 40, message = "Le prénom doit comporter moins de 40 caractères")
    private String firstname;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 40, message = "Le nom doit comporter moins de 40 caractères")
    private String lastname;

    @NotBlank(message = "email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Size(max = 30, message = "L'email doit comporter moins de 30 caractères.")
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Size(min = 8, max = 15, message = "Le numéro de téléphone doit comporter entre 8 et 15 caractères")
    @Pattern(regexp = "\\d{8,15}", message = "Le numéro de téléphone doit comporter entre 8 et 15 chiffres")
    private String phoneNumber;

    @NotBlank(message = "L'objet est obligatoire")
    @Size(max = 100, message = "L'objet doit comporter moins de 100 caractères")
    private String subject;

    @NotBlank(message = "La description est obligatoire")
    private String description;

}
