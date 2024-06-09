package com.elife.mandra.Web.Requests.UserForms;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdatePasswordForm {

    @NotBlank(message = "Old password is required")
    @Size(min = 8, message = "Old password must be at least 8 characters long")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 30, message = "New password must be between 8 and 30 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
             message = "New password must contain at least one letter, one number, and one special character")
    private String newPassword;

    @NotBlank(message = "Repeat password is required")
    private String repeatPassword;

    // Custom validator to check if newPassword matches repeatPassword
    @AssertTrue(message = "New password and repeat password do not match")
    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(repeatPassword);
    }


}
