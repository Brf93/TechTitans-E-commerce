package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class KeyboardRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String style;

    private String backlit;

    private String color;

    private String wireless;

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

    public String getStyle() {
        return style;
    }

    public String getBacklit() {
        return backlit;
    }

    public String getColor() {
        return color;
    }

    public String getWireless() {
        return wireless;
    }
}
