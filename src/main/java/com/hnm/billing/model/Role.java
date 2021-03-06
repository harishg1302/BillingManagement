package com.hnm.billing.model;

public enum Role {
    CUSTOMER(1, "Customer"),
    ADMIN(2, "Admin");

    private int id;
    private String displayName;

    Role(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
