package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class CpuFanRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String rpm;

    private String color;

    private String noiseLevel;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
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

    public String getRpm() {
        return rpm;
    }

    public String getColor() {
        return color;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }
}
