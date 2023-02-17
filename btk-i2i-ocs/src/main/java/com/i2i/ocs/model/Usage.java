package com.i2i.ocs.model;

public class Usage {
    private String msisdn;
    private int subsKey;
    private String service;
    private int amount;


    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getSubsKey() {
        return subsKey;
    }

    public void setSubsKey(int subsKey) {
        this.subsKey = subsKey;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
