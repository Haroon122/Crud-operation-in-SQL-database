package com.example.crudstudent;

public class StudentModel {

    private String name;
    private String studentClass;
    private String rollNo;

    public StudentModel(String name, String studentClass, String rollNo) {
        this.name = name;
        this.studentClass = studentClass;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
