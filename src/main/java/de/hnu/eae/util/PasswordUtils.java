package de.hnu.eae.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This class provides utility methods for hashing and verifying passwords using BCrypt algorithm.
 */
public class PasswordUtils {

    /**
     * Hashes the given plain text password using BCrypt algorithm.
     *
     * @param plainTextPassword the plain text password to be hashed
     * @return the hashed password
     */
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Verifies the given plain text password against the hashed password using BCrypt algorithm.
     *
     * @param plainTextPassword the plain text password to be verified
     * @param hashedPassword the hashed password to be compared against
     * @return true if the plain text password matches the hashed password, false otherwise
     */
    public static boolean verifyUserPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}