package com.andy.baselibrary.map_nav;

/**
 * Created by Andy on 2017/7/25.
 */

public class PackageData {
    private String packageName;
    private String label;
    private boolean isInstalled;

    public PackageData(String packageName) {
        this.packageName = packageName;
    }

    public PackageData() {
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }
}
