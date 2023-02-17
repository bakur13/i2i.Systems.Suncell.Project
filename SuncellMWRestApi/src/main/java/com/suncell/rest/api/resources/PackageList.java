package com.suncell.rest.api.resources;

public class PackageList {

	private long packageId;
    private String packageName;

    public PackageList(long packageId, String packageName) {
        this.packageId = packageId;
        this.packageName = packageName;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
