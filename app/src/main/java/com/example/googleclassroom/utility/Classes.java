package com.example.googleclassroom.utility;

import java.io.Serializable;
import java.util.ArrayList;

public class Classes implements Serializable {
    String className, description, action, productor;
    int roomNumber, classCode, studentsNumber;
    ArrayList<String> students = new ArrayList<>();
    ArrayList<String> masters = new ArrayList<>();

    public Classes(String action, String className, int roomNumber, String description) {
        this.action = action;
        this.className = className;
        this.roomNumber = roomNumber;
        this.description = description;
    }

    public void addStudent(String student) {
        students.add(student);
    }

    public int studentsNumber() {
        return students.size();
    }

    public void addMaster(String master) {
        masters.add(master);
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
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

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getMasters() {
        return masters;
    }

    public void setMasters(ArrayList<String> masters) {
        this.masters = masters;
    }

    public ArrayList<String> getStudents() {
        return students;
    }
}
