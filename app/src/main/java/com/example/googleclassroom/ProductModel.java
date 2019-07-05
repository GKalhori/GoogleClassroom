package com.example.googleclassroom;

public class ProductModel {
    String className, masterName;
    int studentsNumber,imageId;

    public ProductModel(int imageId,String className, String masterName, int studentsNumber) {
        this.imageId = imageId;
        this.className = className;
        this.masterName = masterName;
        this.studentsNumber = studentsNumber;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getClassName() {
        return className;
    }

    public String getMasterName() {
        return masterName;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public int getImageId() {
        return imageId;
    }
}
