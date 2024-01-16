package de.hnu.eae;

import de.hnu.eae.data.User;
import de.hnu.eae.data.UserDAO;
import de.hnu.eae.data.PasswordUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * This class provides authentication services for user login and token generation.
 */
@Stateless
public class AuthenticationService {

    private static final String SECRET_KEY = "very-secure-secret-key"; // Replace with a strong key

    @Inject
    private UserDAO userDAO;

    /**
     * Checks if the provided password matches the password of the user with the given username.
     *
     * @param username the username of the user
     * @param password the password to be checked
     * @return true if the password matches, false otherwise
     */
    public boolean checkPassword(String username, String password) {
        User user = userDAO.findUserByUsername(username);
        if (user != null) {
            return PasswordUtils.verifyUserPassword(password, user.getPassword());
        }
        return false;
    }
    
    /**
     * Generates a JWT token for the given user.
     *
     * @param user the user for whom the token is generated
     * @return the generated JWT token
     * @throws Exception if there is an error creating the JWT token
     */
    public String generateToken(User user) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("auth0")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hour expiration
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new Exception("Error creating JWT for user: " + user.getUsername(), exception);
        }
    }

    public String hashPassword(String plainTextPassword) {
        return PasswordUtils.hashPassword(plainTextPassword);
    }
    
}
