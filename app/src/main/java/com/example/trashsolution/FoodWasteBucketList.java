package com.example.trashsolution;

import java.util.ArrayList;

public class FoodWasteBucketList {
    private ArrayList<FoodWasteBucket> buckets = new ArrayList<>();
    private double[] lat = {35.885870, 35.884851, 35.884236, 35.884708, 35.885159};
    private double[] lang = {128.605367, 128.607256, 128.608721, 128.611076, 128.611076, 128.612417};

    FoodWasteBucketList() {
        for (int i = 0; i < 5; i++) {
            this.buckets.add(new FoodWasteBucket(new Location(lat[i], lang[i]), "FoodWasteBucket" + i, new Capacity()));
        }
    }

    public FoodWasteBucket getBucketByIndex(int id) {
        return buckets.get(id);
    }

    public FoodWasteBucket getBucketBySerialNumber(String serialNumber) {
        for (int i = 0; i < buckets.size(); i++) {
            FoodWasteBucket temp = buckets.get(i);
            if(temp.getSerialNumber() == serialNumber) {
                return temp;
            }
        }
        return null;
    }
}
