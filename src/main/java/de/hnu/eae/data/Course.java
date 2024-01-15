package de.hnu.eae.data;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;

    // Field to store the path to the course material
    @Column(name = "material_path")
    private String materialPath;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "courseId", referencedColumnName = "courseId"), inverseJoinColumns = @JoinColumn(name = "studentId", referencedColumnName = "id"))
    private Set<Student> enrolledStudents;

    public Course() {
    }

    public Course(String courseName, String description, Lecturer lecturer) {
        this.courseName = courseName;
        this.description = description;
        this.lecturer = lecturer;
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

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getMaterialPath() {
        return materialPath;
    }

    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}