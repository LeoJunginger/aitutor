package de.hnu.eae.data;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends User {

    @Column(name = "matrNr", unique = true, nullable = false)
    private long matrNr;

    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Course> enrolledCourses;

    /* @OneToMany(mappedBy = "student")
    private Set<CourseFeedback> courseFeedback;

    */
    public Student() {
        super();
    }

    public Student(long id, String firstname, String lastname, Date dateOfBirth, String placeOfBirth, String nationality, String countryOfResidence) {
        super(id, firstname, lastname, dateOfBirth, placeOfBirth, nationality, countryOfResidence);
    }

    public long getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(long matrNr) {
        this.matrNr = matrNr;
    }

    
}
