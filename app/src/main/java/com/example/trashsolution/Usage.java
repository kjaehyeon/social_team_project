package com.example.trashsolution;

import java.io.Serializable;

public class Usage implements Serializable {
    public int totalUsage;
    public int monthlyUsage;

    Usage() {
        this.totalUsage = 0;
        this.monthlyUsage = 0;
    }
    Usage(int totalUsage, int monthlyUsage){
        this.totalUsage = totalUsage;
        this.monthlyUsage = monthlyUsage;
    }
    public void setUsage(int totalUsage, int monthlyUsage){
        this.totalUsage = totalUsage;
        this.monthlyUsage = totalUsage;
    }
    public int getTotalUsage(){
        return totalUsage;
    }
    public int getMonthlyUsage(){
        return monthlyUsage;
    }
}