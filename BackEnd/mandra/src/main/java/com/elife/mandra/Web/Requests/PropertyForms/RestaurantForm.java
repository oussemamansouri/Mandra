package com.elife.mandra.Web.Requests.PropertyForms;

import jakarta.validation.constraints.Email;
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
public class RestaurantForm {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 40, message = "Le nom doit comporter moins de 40 caractères")
    private String name;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @Email(message = "L'email doit être valide")
    @Size(max = 40, message = "L'email doit comporter moins de 40 caractères")
    private String email;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 100, message = "L'adresse doit comporter moins de 100 caractères")
    private String address;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 30, message = "La ville doit comporter moins de 30 caractères")
    private String city;

    @NotNull(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "\\d{8,15}", message = "Le numéro de téléphone doit comporter entre 8 et 15 chiffres")
    private String phoneNumber;

    @Size(max = 100, message = "L'URL du site web doit comporter moins de 100 caractères")
    @Pattern(regexp = "^(http|https)://.*", message = "L'URL du site web doit être valide")
    private String website;

    @NotNull(message = "La disponibilité du parking est obligatoire")
    private boolean hasParking;

    @NotNull(message = "La disponibilité du wifi est obligatoire")
    private boolean hasWifi;

    @NotNull(message = "La politique des animaux est obligatoire")
    private boolean allowsPets;

    @Size(max = 100, message = "L'URL de Facebook doit comporter moins de 100 caractères")
    @Pattern(regexp = "^(http|https)://.*", message = "L'URL de Facebook doit être valide")
    private String facebook;

    @Size(max = 100, message = "L'URL d'Instagram doit comporter moins de 100 caractères")
    @Pattern(regexp = "^(http|https)://.*", message = "L'URL d'Instagram doit être valide")
    private String instagram;

    @NotNull(message = "La disponibilité du terrasse est obligatoire")
    private boolean withTerrace;

    @NotNull(message = "L'acceptation de réservation est obligatoire")
    private boolean acceptsReservation;

}
