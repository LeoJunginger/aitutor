package de.hnu.eae.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * This class represents a Data Access Object (DAO) for managing User entities.
 * It provides methods for retrieving User objects from the database.
 */
@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "AITutorDB")
    private EntityManager em;

    /**
     * Retrieves a User object from the database based on the given username.
     *
     * @param username the username of the User to retrieve
     * @return the User object with the given username, or null if no such User exists
     */
    public User findUserByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createUser(User user) {
        em.persist(user);
    }
    
}
