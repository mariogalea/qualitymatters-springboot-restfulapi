package io.qualitymatters.restfulapi;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity is a JPA Annotation to make this object ready for storage in a JPA-based data store.
 * The Booking Object is has the below JSON Properties.
 * 
 * 
 * JSON Structure 
    {
        "id": 1,
        "firstname": "Mario",
        "lastname": "Galea"
    }

 **/


@Entity
public class Booking {

    private @Id @GeneratedValue Long id;
    private String  firstName;
    private String  lastName;


    Booking() {

    }

    Booking(String firstName, String lastName) {

        this.firstName       = firstName;
        this.lastName        = lastName;

    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Booking))
      return false;

    Booking booking = (Booking) o;
    return Objects.equals(this.id, booking.id) 
        && Objects.equals(this.firstName, booking.firstName)
        && Objects.equals(this.lastName, booking.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.firstName, this.lastName);
  }

  @Override
  public String toString() {
    return "Booking{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + '}';
  }

}