package com.hk.utils;

public class OrderDetail {
    private String name;
    private String type;
    private Double price;
    private Double value;

    public OrderDetail(String name, String type, Double price, Double value) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.value = value;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", value=" + value +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
