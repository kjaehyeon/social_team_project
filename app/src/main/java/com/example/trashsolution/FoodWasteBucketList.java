package com.example.trashsolution;

import java.util.ArrayList;

public class FoodWasteBucketList {
    public ArrayList<FoodWasteBucket> buckets = new ArrayList<>();
    private double[] lat = {35.885907, 35.884851, 35.884236, 35.884708, 35.885159};
    private double[] lang = {128.605364, 128.607256, 128.608721, 128.611076, 128.612417};
    private double[] cap = { 30.5, 105.3, 200, 42.6, 123.1};

    FoodWasteBucketList() {
        for (int i = 0; i < 5; i++) {
            this.buckets.add(new FoodWasteBucket(new Location(lat[i], lang[i]), "FoodWasteBucket" + i, new Capacity(cap[i])));
        }
    }

    public FoodWasteBucket getBucketByIndex(int id) {
        return buckets.get(id);
    }

    public FoodWasteBucket getBucketBySerialNumber(String serialNumber) {
        for (int i = 0; i < buckets.size(); i++) {
            FoodWasteBucket temp = buckets.get(i);
            if(serialNumber.equals(temp.serialNumber)) {
                return temp;
            }
        }
        return null;
    }
    public void setBucketBySerialNumber(String serialNumber, FoodWasteBucket bucket) {
        for (int i = 0; i < buckets.size(); i++) {
            FoodWasteBucket temp = buckets.get(i);
            if(serialNumber.equals(temp.serialNumber)) {
                buckets.set(i, bucket);
            }
        }
    }
    public int getBucketNum(){
        return buckets.size();
    }

}
