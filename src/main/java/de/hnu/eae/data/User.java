package de.hnu.eae.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "public", name = "users")
public class User {
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long id;
    // @Column(nullable = false, unique = true)
    // private long matrNr;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length=20)
    private String role;
    @Column(length=30)
    private String firstname;
    @Column(length=30)
    private String lastname;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(length=20)
    private String nationality;
    @ManyToMany
    @JoinTable(
        name = "user_course",
        joinColumns = @javax.persistence.JoinColumn(name = "id"), 
        inverseJoinColumns = @javax.persistence.JoinColumn(name = "courseId")  
    )
    private Set<Course> courses = new HashSet<>();


    public User(long id, String username, String password, String role, String firstname, String lastname, Date dateOfBirth, 
            String nationality) {
        this.id = id;
        // this.matrNr = matrNr;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Role {
        STUDENT, LECTURER
    }   

    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    /*@ManyToMany(mappedBy = "enrolledStudents")
    private Set<Course> enrolledCourses;

    /* @OneToMany(mappedBy = "student")
    private Set<CourseFeedback> courseFeedback;

    public long getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(long matrNr) {
        this.matrNr = matrNr;
    }

    /* public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    } */

    /* public Set<CourseFeedback> getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(Set<CourseFeedback> courseFeedback) {
        this.courseFeedback = courseFeedback;
    }
    */

    
}
