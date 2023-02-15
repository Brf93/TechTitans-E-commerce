package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class GpuRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String storageInterface;

    private String memory;

    private String clockSpeed;

    private String chipset;

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

    public String getStorageInterface() {
        return storageInterface;
    }

    public String getMemory() {
        return memory;
    }

    public String getClockSpeed() {
        return clockSpeed;
    }

    public String getChipset() {
        return chipset;
    }
}
