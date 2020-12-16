package com.example.trashsolution;

public class FoodWasteBucket {
    public Location location;
    public String serialNumber;
    public Capacity capacity;

    FoodWasteBucket() {
        this.capacity = new Capacity();
        this.location = new Location();
        this.serialNumber = "";
    }
    FoodWasteBucket(Location location, String serialNumber, Capacity capacity) {
        this.capacity = capacity;
        this.location = location;
        this.serialNumber = serialNumber;
    }

    public void setBucketInfo(Location location, String serialNumber, Capacity capacity) {
        this.capacity = capacity;
        this.location = location;
        this.serialNumber = serialNumber;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }
    public Capacity getCapacity() {
        return capacity;
    }
    public Location getLocation() {
        return location;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
}
