package com.suncell.rest.api.resources;

import java.util.Date;

public class Subscriber {

	private  int subscriberId;
    private  String msisdn;
    private  String name;
    private  String surname;
    private  String email;
    private  String password;
    private  Date sDate;
    private  String status;
    private  String securityQuesstion;

    

    public Subscriber(long subscriberId, String MSISDN, String name, String surname, String email, String password, Date sDate, String status, String securityQuesstion) {
        this.subscriberId = (int)subscriberId;
        this.msisdn = MSISDN;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sDate = sDate;
        this.status = status;
        this.securityQuesstion = securityQuesstion;
    }
    
    public Subscriber(String MSISDN, String password) {
        this.msisdn = MSISDN;
        this.password = password;
    }
    public String getSecurityQuesstion() {
        return securityQuesstion;
    }
    public String getPassword() {
        return password;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getsDate() {
        return sDate;
    }

    public String getStatus() {
        return status;
    }
}
