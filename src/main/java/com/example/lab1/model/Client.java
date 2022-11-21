package com.example.lab1.model;

public class Client {
    private int clientId;
    private String clientName;
    private String cityName;
    private String clientEmail;

    public Client(int clientId, String clientName, String cityName, String clientEmail) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.cityName = cityName;
        this.clientEmail = clientEmail;
    }

    public Client(String clientName, String cityName, String clientEmail) {
        this.clientId = -1;
        this.clientName = clientName;
        this.cityName = cityName;
        this.clientEmail = clientEmail;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public String toString() {

        return "{\"clientId\": " + clientId + ", \"clientName\": \"" + clientName + "\"" +
                ", \"cityName\": \"" + cityName + "\", \"clientEmail\": \"" + clientEmail + "\"}";
    }
}
