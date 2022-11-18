package com.enigmacamp.simplejdbc.model.model;

import java.util.Date;

public class Customer {
    private Integer id;
    private String name;
    private String address;
    private Date birthDate;
    private String status;
    private String phone;

    public Customer(Integer id, String name, String address, Date birthDate, String status, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.status = status;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
