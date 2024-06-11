package com.elife.mandra.Web.Requests.OwnerForms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

public class AddOwnerForm {

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

    @NotNull(message = "Le numéro de téléphone est obligatoire")
    private int phoneNumber ;

    @NotBlank(message = "Le preuve est obligatoire")
    private String proof;

    @NotBlank(message = "L'image de cin est obligatoire")
    private String cinImage ;

    @NotNull(message = "Le nombre des hôtels est obligatoire")
    @Min(value = 0, message = "Le nombre des hôtels doit être au moins 0")
    @Max(value = 10, message = "Le nombre des hôtels doit être au plus 10")
    private Integer nbOfHotels;

    @NotNull(message = "Le nombre des restaurants est obligatoire")
    @Min(value = 0, message = "Le nombre des restaurants doit être au moins 0")
    @Max(value = 10, message = "Le nombre des restaurants doit être au plus 10")
    private Integer nbOfRestaurant;

    @NotNull(message = "Le nombre des maisons d'hôtes est obligatoire")
    @Min(value = 0, message = "Le nombre des maisons d'hôtes doit être au moins 0")
    @Max(value = 10, message = "Le nombre des maisons d'hôtes doit être au plus 10")
    private Integer nbOfGuesthouses;

}
