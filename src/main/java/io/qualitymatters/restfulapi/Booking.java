package io.qualitymatters.restfulapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

/**
    {
        "id": 1,
        "firstname": "Mario",
        "lastname": "Galea",
        "totalprice": 600,
        "depositpaid": true,
        "checkin": "2013-02-23",
        "additionalneeds": "Swimming Pool Access Granted"
    }

    N.B.
    For simplicity. totalprice is of type Integer.  Whenever dealing with currency, always use BigDecimal in Java.
 */

@Entity
public class Booking {

    private @Id @GeneratedValue Long id;
    private String  firstName;
    private String  lastName;
    private Integer totalPrice;
    private Boolean isDepositPaid;
    private Date    checkIn;
    private String  additionalNeeds;

    Booking() {

    }

    Booking(String firstName, String lastName, Integer totalPrice, Boolean isDepositPaid, Date checkIn, String additionalNeeds) {

        this.firstName       = firstName;
        this.lastName        = lastName;
        this.totalPrice      = totalPrice;
        this.isDepositPaid   = isDepositPaid;
        this.checkIn         = checkIn;
        this.additionalNeeds = additionalNeeds;

    }

    @JsonProperty("id")
    public Long getId() {
        return this.id;
    }

    @JsonProperty("firstname")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastname")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("totalprice")
    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("depositpaid")
    public boolean getIsDepositPaid() {
        return this.isDepositPaid;
    }

    public void setIsDepositPaid(boolean isDepositPaid) {
        this.isDepositPaid = isDepositPaid; }

    @JsonProperty("checkin")
    public Date getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }


    @JsonProperty("additionalneeds")
    public String getAdditionalNeeds() {
        return this.additionalNeeds;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }
}