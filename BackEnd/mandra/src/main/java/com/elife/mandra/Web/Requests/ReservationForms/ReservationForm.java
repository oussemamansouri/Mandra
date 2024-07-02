package com.elife.mandra.Web.Requests.ReservationForms;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationForm {

    @NotBlank(message = "Le nom de l'hôtel est obligatoire")
    private String hotelName;

    @NotBlank(message = "Le nom du client est obligatoire")
    private String clientName;

    @NotNull(message = "La date d'arrivée est obligatoire")
    @FutureOrPresent(message = "La date d'arrivée doit être présente ou future")
    private Date checkInDate;

    @NotNull(message = "La date de départ est obligatoire")
    @FutureOrPresent(message = "La date de départ doit être présente ou future")
    private Date checkOutDate;

    @Min(value = 1, message = "Le nombre d'adultes doit être d'au moins 1")
    private int numOfAdults;

    @Min(value = 0, message = "Le nombre d'enfants ne peut pas être négatif")
    private int numOfChildren;

    @Min(value = 1, message = "Le nombre de chambres doit être d'au moins 1")
    private int numOfRooms;

}
