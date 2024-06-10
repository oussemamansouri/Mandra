package com.elife.mandra.Web.Requests.UserForms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Mot de passe obligatoire")
    private String password ;

    @NotNull(message = "Le numéro de téléphone est obligatoire")
    private int phoneNumber ;

}
