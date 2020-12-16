package com.example.trashsolution;

public class DumpFoodWaste {
    public void dumpTransaction(Customer user, String serialNumber, double weight) {
        FoodWasteBucket bucket = new FoodWasteBucketList().getBucketBySerialNumber(serialNumber);
        //쓰레기통에 버릴 무게 추
        double curCapacity = bucket.capacity.getCurrentCapacity();
        bucket.capacity.setCurrentCapacity(curCapacity);
        //사용자가 버린 무게 추가

    }
}
