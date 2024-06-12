package com.elife.mandra.Web.Requests.UserForms;

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

public class AddUserForm {

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 20, message = "Le prénom doit comporter moins de 20 caractères")
    private String firstname ;

    @NotBlank(message = "Le nom de famille est obligatoire")
    @Size(max = 20, message = "Le nom de famille doit comporter moins de 20 caractères.")
    private String lastname ;

    @NotBlank(message = "email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Size(max = 30, message = "L'email doit comporter moins de 30 caractères.")
    private String email ;

    @NotBlank(message = "Nouveau mot de passe requis")
    @Size(min = 8, max = 30, message = "Le mot de passe doit comporter entre 8 et 30 caractères")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
             message = "Le mot de passe doit contenir au moins une lettre, un chiffre et un caractère spécial")
    private String password ;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Size(min = 8, max = 15, message = "Le numéro de téléphone doit comporter entre 8 et 15 caractères")
    @Pattern(regexp = "\\d{8,15}", message = "Le numéro de téléphone doit comporter entre 8 et 15 chiffres")
    private String phoneNumber ;

}
