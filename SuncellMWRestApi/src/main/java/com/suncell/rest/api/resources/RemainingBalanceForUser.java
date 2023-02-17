package com.suncell.rest.api.resources;

public class RemainingBalanceForUser {

	private int data;
    private int sms;
    private int voice;
    private int price;


    public RemainingBalanceForUser(long data, long sms, long voice, long price) {
        this.data = (int)data;
        this.sms = (int)sms;
        this.voice = (int)voice;
        this.price = (int)price;
    }

    public RemainingBalanceForUser(long data, long sms, long voice) {
        this.data = (int)data;
        this.sms = (int)sms;
        this.voice = (int)voice;
    }

    public RemainingBalanceForUser() {
    }

    public long getData() {
        return data;
    }

    public void setData(long data2) {
        this.data = (int)data2;
    }

    public long getSms() {
        return sms;
    }

    public void setSms(long sms) {
        this.sms = (int)sms;
    }

    public long getVoice() {
        return voice;
    }

    public void setVoice(long voice) {
        this.voice = (int)voice;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = (int)price;
    }
}
