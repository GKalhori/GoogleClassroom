package com.example.googleclassroom;

public class ClassData {
    private int id;
    String className, description, masterName;
    int image, roomNumber;

    public ClassData(String className, String masterName, String description, int roomNumber, int image) {
        this.className = className;
        this.masterName = masterName;
        this.description = description;
        this.roomNumber = roomNumber;
        this.image = image;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getClassName() {
        return className;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getDescription() {
        return description;
    }
}
