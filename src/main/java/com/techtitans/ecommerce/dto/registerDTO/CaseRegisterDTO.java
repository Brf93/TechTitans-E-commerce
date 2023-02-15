package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class CaseRegisterDTO {
    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String sidePanel;

    private String color;

    private String cabinetType;

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

    public String getSidePanel() {
        return sidePanel;
    }

    public String getColor() {
        return color;
    }

    public String getCabinetType() {
        return cabinetType;
    }
}
