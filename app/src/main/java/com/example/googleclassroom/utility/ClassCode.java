package com.example.googleclassroom.utility;

import java.io.Serializable;

public class ClassCode implements Serializable {

    String state;
    int classCode;

    public ClassCode(String state, int classCode) {
        this.state = state;
        this.classCode = classCode;
    }

    public int getClassCode() {
        return classCode;
    }

    public String getState() {
        return state;
    }
}
