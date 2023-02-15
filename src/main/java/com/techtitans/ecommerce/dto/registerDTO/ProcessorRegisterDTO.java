package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class ProcessorRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String speed;

    private String socketType;

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

    public String getSpeed() {
        return speed;
    }

    public String getSocketType() {
        return socketType;
    }
}
