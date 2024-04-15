package com.mycompany.testing_project;
public class DepartmentDetails {
    private String departmentName;
    private String location;
    // Add other relevant attributes and methods as needed

    public DepartmentDetails(String departmentName, String location) {
        this.departmentName = departmentName;
        this.location = location;
    }

    // Getters and setters for department details
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
