package de.hnu.eae.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * This class represents a Data Access Object (DAO) for the Course entity.
 * It provides methods to create, find, and update Course objects in the database.
 */
public class CourseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a new Course object in the database.
     *
     * @param course The Course object to be created.
     */
    @Transactional
    public void createCourse(Course course) {
        entityManager.persist(course);
    }

    /**
     * Finds a Course object in the database by its ID.
     *
     * @param courseId The ID of the Course object to be found.
     * @return The found Course object, or null if not found.
     */
    @Transactional
    public Course findCourse(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    /**
     * Updates a Course object in the database.
     *
     * @param course The Course object to be updated.
     */
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }
}
