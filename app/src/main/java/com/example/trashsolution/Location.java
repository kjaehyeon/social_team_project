package com.example.trashsolution;

public class Location {
    private double longitude;
    private double latitude;

    Location() {
        this.longitude = 0;
        this.latitude = 0;
    }
    Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
}
