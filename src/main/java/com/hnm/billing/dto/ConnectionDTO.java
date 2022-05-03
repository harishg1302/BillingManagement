package com.hnm.billing.dto;

import com.hnm.billing.model.Connection;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ConnectionDTO {

    private String emailId;
    private List<Connection> connectionList;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }
}
