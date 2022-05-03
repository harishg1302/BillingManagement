package com.hnm.billing.model;

public enum BillStatus {
    PENDING(1, "Pending"),
    PAID(2, "Paid"),
    OVERDUE(3, "Overdue");

    private int id;
    private String displayName;

    BillStatus(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
