package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class MotherboardRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String formFactor;

    private String chipset;

    private Integer memorySlots;

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

    public String getFormFactor() {
        return formFactor;
    }

    public String getChipset() {
        return chipset;
    }

    public Integer getMemorySlots() {
        return memorySlots;
    }

    public String getSocketType() {
        return socketType;
    }
}
