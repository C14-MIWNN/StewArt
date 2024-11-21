package nl.miwnn.se14.StewArt.stewart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Luc Weerts
 * Capture the information needed for the create-a-new-user form
 */
public class StewArtUserDTO {
    @NotBlank
    private String username;

    @Size(min = 6, max = 128)
    private String password;
    private String passwordConfirm;

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @Size(min = 6, max = 128) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 128) String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
