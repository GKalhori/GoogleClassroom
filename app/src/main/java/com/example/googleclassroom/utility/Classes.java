package com.example.googleclassroom.utility;

public class Classes {
    String className, description,action;
    int roomNumber, classCode;

    public Classes(String action,String className, int roomNumber, String description) {
        this.action = action;
        this.className = className;
        this.roomNumber = roomNumber;
        this.description = description;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }
}
