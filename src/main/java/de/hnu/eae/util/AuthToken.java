package de.hnu.eae.util;

/**
 * Represents an authentication token.
 */
public class AuthToken {
    private String token;

    /**
     * Constructs a new AuthToken object with the specified token.
     * 
     * @param token the authentication token
     */
    public AuthToken(String token) {
        this.token = token;
    }

    /**
     * Retrieves the authentication token.
     * 
     * @return the authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authentication token.
     * 
     * @param token the authentication token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
