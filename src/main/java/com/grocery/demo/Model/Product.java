package com.grocery.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity

@Table(name = "Products")
public class Product {
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Long getMilleage() {
        return milleage;
    }

    public void setMilleage(Long milleage) {
        this.milleage = milleage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotEmpty(message = "Car Image must be provided")
    private String carImage;
    @NotEmpty(message = "Car model can not be blank")
    private String carModel;
   // @NotEmpty(message = "Milleage must be disclosed")
    private Long milleage;
   // @NotEmpty(message = "Price field is mandatory")
    private double price;
    @NotEmpty(message = "Provide the location of the car")
    private String location;

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    private String dealerName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    private Order order;
    private String status;


}
