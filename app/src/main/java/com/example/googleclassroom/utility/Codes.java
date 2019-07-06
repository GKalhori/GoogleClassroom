package com.example.googleclassroom.utility;

import java.io.Serializable;

public class Codes implements Serializable {

    int classCode;

    public Codes(int classCode) {
        this.classCode = classCode;
    }

    public int getClassCode() {
        return classCode;
    }

}