
package de.hsalbsig.HonigruehrmaschineSteuerung.dto;

import java.io.Serializable;

/**
 * @author
 */

public class UserDTO implements Serializable {
    private String username;
    private String password;
    private String email;
    private String password_confirmation;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

}
