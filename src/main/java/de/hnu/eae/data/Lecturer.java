package de.hnu.eae.data;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "lecturer")
public class Lecturer extends User {

    @OneToMany(mappedBy = "lecturer")
    private Set<Course> createdCourses;

    public Lecturer() {
        super();
    }

    public Lecturer(long id, String firstname, String lastname, Date dateOfBirth, String placeOfBirth, String nationality, String countryOfResidence) {
        super(id, firstname, lastname, dateOfBirth, placeOfBirth, nationality, countryOfResidence);
    }

    public Set<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(Set<Course> createdCourses) {
        this.createdCourses = createdCourses;
    }

}
