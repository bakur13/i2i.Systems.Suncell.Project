package com.suncell.rest.api.resources;

import java.util.Date;

public class TotalBalanceForAllUsers {

	private int SUBSC_ID;
    private int PACKAGE_ID;
    private int BAL_LVL_VOICE;
    private int BAL_LVL_SMS;
    private int BAL_LVL_DATA;
    private Date SDATE;
    private Date EDATE;

    public TotalBalanceForAllUsers(int SUBSC_ID, int PACKAGE_ID, int BAL_LVL_VOICE, int BAL_LVL_SMS, int BAL_LVL_DATA, Date SDATE, Date EDATE) {
        this.SUBSC_ID = SUBSC_ID;
        this.PACKAGE_ID = PACKAGE_ID;
        this.BAL_LVL_VOICE = BAL_LVL_VOICE;
        this.BAL_LVL_SMS = BAL_LVL_SMS;
        this.BAL_LVL_DATA = BAL_LVL_DATA;
        this.SDATE = SDATE;
        this.EDATE = EDATE;
    }

    public long getSUBSC_ID() {
        return SUBSC_ID;
    }

    public void setSUBSC_ID(int SUBSC_ID) {
        this.SUBSC_ID = SUBSC_ID;
    }

    public long getPACKAGE_ID() {
        return PACKAGE_ID;
    }

    public void setPACKAGE_ID(int PACKAGE_ID) {
        this.PACKAGE_ID = PACKAGE_ID;
    }

    public long getBAL_LVL_VOICE() {
        return BAL_LVL_VOICE;
    }

    public void setBAL_LVL_VOICE(int BAL_LVL_VOICE) {
        this.BAL_LVL_VOICE = BAL_LVL_VOICE;
    }

    public long getBAL_LVL_SMS() {
        return BAL_LVL_SMS;
    }

    public void setBAL_LVL_SMS(int BAL_LVL_SMS) {
        this.BAL_LVL_SMS = BAL_LVL_SMS;
    }

    public long getBAL_LVL_DATA() {
        return BAL_LVL_DATA;
    }

    public void setBAL_LVL_DATA(int BAL_LVL_DATA) {
        this.BAL_LVL_DATA = BAL_LVL_DATA;
    }

    public Date getSDATE() {
        return SDATE;
    }

    public void setSDATE(Date SDATE) {
        this.SDATE = SDATE;
    }

    public Date getEDATE() {
        return EDATE;
    }

    public void setEDATE(Date EDATE) {
        this.EDATE = EDATE;
    }
}
