package com.hnm.billing.model;

public enum ConnectionType {
    MOBILE_RECHARGE(1, "Mobile Recharge"),
    ELECTRICITY(2, "Electricity"),
    DTH(3, "DTH"),
    GAS(4, "Gas"),
    WATER(5, "Water"),
    BROADBAND(6, "WiFi");

    private int id;
    private String displayName;

    ConnectionType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
