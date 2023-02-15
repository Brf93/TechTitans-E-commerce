package com.techtitans.ecommerce.dto.registerDTO;

import java.util.List;

public class StorageRegisterDTO {

    private String code;

    private String name;

    private String link;

    private List<String> img;

    private Double price;

    private String brand;

    private String model;

    private String storageInterface;

    private String rpm;

    private String StorageType;

    private String cacheMemory;

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

    public String getStorageInterface() {
        return storageInterface;
    }

    public String getRpm() {
        return rpm;
    }

    public String getStorageType() {
        return StorageType;
    }

    public String getCacheMemory() {
        return cacheMemory;
    }
}
