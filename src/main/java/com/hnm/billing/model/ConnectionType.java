package com.hnm.billing.model;

public enum ConnectionType {
    MOBILE(1, "Mobile"),
    ELECTRICITY(2, "Electricity"),
    DTH(3, "DTH"),
    GAS(4, "Gas"),
    WATER(5, "Water"),
    WIFI(6, "WiFi");

    private int    id;
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
