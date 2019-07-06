package com.example.googleclassroom.utility;

import java.io.Serializable;
import java.util.ArrayList;

public class Classes implements Serializable {
    String className, description, action;
    int classCode;
    int roomNumber;
    ArrayList<String> students = new ArrayList<>();
    ArrayList<String> masters = new ArrayList<>();

    public Classes(String action, String className, int roomNumber, String description) {
        this.action = action;
        this.className = className;
        this.roomNumber = roomNumber;
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public String getClassName() {
        return className;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
