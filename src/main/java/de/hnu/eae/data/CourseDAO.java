package de.hnu.eae.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * This class represents a Data Access Object (DAO) for the Course entity.
 * It provides methods to create, find, and update Course objects in the database.
 */
@RequestScoped
public class CourseDAO {

    @PersistenceContext(unitName = "AITutorDB")
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
     * Finds the latest Course object in the database by its name.
     *
     * @param courseName The name of the Course object to be found.
     * @return The latest Course object with the given name, or null if not found.
     */
    @Transactional
    public Course findCoursebyName(String courseName) {
        TypedQuery<Course> query = entityManager.createQuery(
            "SELECT c FROM Course c WHERE c.courseName = :name ORDER BY c.courseId DESC", Course.class);
        query.setParameter("name", courseName);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            // Handle the case where no course is found
            return null;
        }
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

    /**
     * Retrieves a list of all courses from the database.
     *
     * @return the list of courses
     */
    @Transactional
    public List<Course> getAllCourses() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }
}
