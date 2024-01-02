package de.hnu.eae.data;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

    private String rights;
    public Admin() {
        super();
    }

    public Admin(long id, String firstname, String lastname, Date dateOfBirth, String placeOfBirth, String nationality, String countryOfResidence) {
        super(id, firstname, lastname, dateOfBirth, placeOfBirth, nationality, countryOfResidence);
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    

}
