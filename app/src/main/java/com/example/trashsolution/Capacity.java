package com.example.trashsolution;

public class Capacity {
    private final double totalCapacity = 200;
    public double currentCapacity;

    Capacity() {
        this.currentCapacity = 50;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    public double getCurrentCapacity() {
        return currentCapacity;
    }
    public boolean isFull(){
        if(currentCapacity < totalCapacity) {
            return true;
        }
        else {
            return false;
        }
    }
}
