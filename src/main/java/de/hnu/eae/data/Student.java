package de.hnu.eae.data;

import java.util.Date;

public class Student {
    private long matrNr;
    private String userId;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String countryOfResidence;

    public Student(long matrNr, String userId, String firstname, String lastname, Date dateOfBirth, String placeOfBirth,
            String nationality, String countryOfResidence) {
        this.matrNr = matrNr;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.nationality = nationality;
        this.countryOfResidence = countryOfResidence;
    }

    public Student() {
        super();
    }

    public long getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(long matrNr) {
        this.matrNr = matrNr;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

}
