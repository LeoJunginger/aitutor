package de.hnu.eae;

import de.hnu.eae.data.User;
import de.hnu.eae.data.UserDAO;
import de.hnu.eae.util.AuthToken;
import de.hnu.eae.data.Credentials;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class represents the resource for authentication-related operations.
 * It provides endpoints for user login, logout, and registration.
 */
@Path("/auth")
public class AuthenticationResource {

    @Inject
    private AuthenticationService authService;

    @Inject
    private UserDAO userDAO; // Inject UserDAO

    /**
     * Endpoint for user login.
     * Validates the user's credentials and generates a JWT token upon successful login.
     *
     * @param credentials The user's login credentials.
     * @return A Response object containing the generated JWT token if login is successful,
     *         or an error message if the credentials are invalid.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(Credentials credentials) {
        try {
            if (authService.checkPassword(credentials.getUsername(), credentials.getPassword())) {
                User user = userDAO.findUserByUsername(credentials.getUsername());
                String token = authService.generateToken(user);
                return Response.ok(new AuthToken(token)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An internal server error occurred.").build();
        }
    }

    /**
     * Endpoint for user logout.
     * Since JWT tokens are stateless, there is no specific action required on the server side.
     * The client should handle removing the JWT token.
     *
     * @return A Response object indicating a successful logout.
     */
    @POST
    @Path("/logout")
    public Response logout() {
        // Implement jwt_token removal on client side
        return Response.ok().entity("User logged out successfully").build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        try {
            // Validate user details
            if (userDAO.findUserByUsername(user.getUsername()) != null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Username already taken").build();
            }

            // Hash the password
            String hashedPassword = authService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            // Save the new user
            userDAO.createUser(user);

            return Response.status(Response.Status.CREATED).entity("User registered successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An internal server error occurred.").build();
        }
    }

}
