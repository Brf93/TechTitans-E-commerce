package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class PowerSupplyRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String power;

    private String color;

    private String efficiency;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<String> getImg() {
        return img;
    }

    public Double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPower() {
        return power;
    }

    public String getColor() {
        return color;
    }

    public String getEfficiency() {
        return efficiency;
    }
}
