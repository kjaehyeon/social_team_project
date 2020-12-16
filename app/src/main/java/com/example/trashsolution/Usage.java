package com.example.trashsolution;

public class Usage {
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