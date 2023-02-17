package com.suncell.rest.api.resources;

public class Package {

	 	private final int packageID;
	    private final String packageName;
	    private final int amountVoice;
	    private final int amountData;
	    private final int amountSms;
	    private final int duration;

	    public Package(int packageID, String packageName, int amountVoice, int amountData, int amountSms, int duration) {
	        this.packageID = packageID;
	        this.packageName = packageName;
	        this.amountVoice = amountVoice;
	        this.amountData = amountData;
	        this.amountSms = amountSms;
	        this.duration = duration;
	    }

	    public long getPackageID() {
	        return packageID;
	    }

	    public String getPackageName() {
	        return packageName;
	    }

	    public long getAmountVoice() {
	        return amountVoice;
	    }

	    public long getAmountData() {
	        return amountData;
	    }

	    public long getAmountSms() {
	        return amountSms;
	    }

	    public long getDuration() {
	        return duration;
	    }
}
