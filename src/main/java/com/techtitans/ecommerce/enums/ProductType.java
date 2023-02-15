package com.techtitans.ecommerce.enums;

public enum ProductType {
    MONITOR("Monitor"),
    CASE("Case"),
    CASE_FAN("Case_Fan"),
    GPU("GPU"),
    CPU_FAN("CPU_Fan"),
    KEYBOARD("Keyboard"),
    MOTHERBOARD("Motherboard"),
    MOUSE("Mouse"),
    POWER_SUPPLY("Power_Supply"),
    PROCESSOR("Processor"),
    RAM("RAM"),
    STORAGE("Storage"),
    MOUSE_PAD("Mouse_Pad");

    private String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
