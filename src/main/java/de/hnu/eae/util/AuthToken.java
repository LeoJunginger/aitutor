package de.hnu.eae.util;

/**
 * Class to encapsulate the JWT token in a response.
 */
public class AuthToken {
    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
