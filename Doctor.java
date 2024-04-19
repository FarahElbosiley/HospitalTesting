package com.mycompany.testing_project;
public class Doctor {
    private int id;
    private String name;
    private int departmentId;
    private String departmentName;

    // Constructor
    public Doctor(int id, String name, int departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
