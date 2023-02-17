package com.suncell.rest.api.resources;

public class TotalBalanceForUser {

		private int data;
	    private int sms;
	    private int voice;

	    public TotalBalanceForUser() {
	    }

	    public TotalBalanceForUser(int data, int sms, int voice) {
	        this.data = data;
	        this.sms = sms;
	        this.voice = voice;
	    }

	    public long getData() {
	        return data;
	    }

	    public void setData(int data) {
	        this.data = data;
	    }

	    public long getSms() {
	        return sms;
	    }

	    public void setSms(int sms) {
	        this.sms = sms;
	    }

	    public long getVoice() {
	        return voice;
	    }

	    public void setVoice(int voice) {
	        this.voice = voice;
	    }
}
