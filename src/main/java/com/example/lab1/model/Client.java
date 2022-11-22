package com.example.lab1.model;

import jakarta.persistence.*;

@Entity
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "client_name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "city_name", nullable = false, length = 50)
    private String city;
    @Basic
    @Column(name = "client_email", nullable = false, length = 50)
    private String email;

    public void update(String clientName, String cityName, String clientEmail){
        setName(clientName);
        setCity(cityName);
        setEmail(clientEmail);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer clientId) {
        this.id = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String clientName) {
        this.name = clientName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cityName) {
        this.city = cityName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String clientEmail) {
        this.email = clientEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (city != null ? !city.equals(client.city) : client.city != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
