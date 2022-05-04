package com.hnm.billing.model;

public enum BillStatus {
    PENDING(1, "PENDING"),
    PAID(2, "PAID"),
    OVERDUE(3, "OVERDUE");

    private int id;
    private String displayName;

    BillStatus(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
