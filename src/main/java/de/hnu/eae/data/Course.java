package de.hnu.eae.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long courseId;
    @Column(nullable = false)
    private String courseName;
    @Column(length = 1000)
    private String description;
    @Column(name = "lecturer")
    private String lecturer;
    // Field to store the path to the course material
    @Column(name = "material_path")
    private String materialPath;
    @ManyToMany(mappedBy = "courses")
    private Set<User> students = new HashSet<>();

    public Course(String courseName, String description, String lecturer, String materialPath, Set<User> students) {
        this.courseName = courseName;
        this.description = description;
        this.lecturer = lecturer;
        this.materialPath = materialPath;
        this.students = students;

    }

    public Course() {
        super();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getMaterialPath() {
        return materialPath;
    }

    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath;
    }

    public Set<User> getEnrolledStudents() {
        return students;
    }

    public void setEnrolledStudents(Set<User> enrolledStudents) {
        this.students = enrolledStudents;
    }
}
