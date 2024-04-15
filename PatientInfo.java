package com.mycompany.testing_project;
public class PatientInfo {
    private int patientID;
    private String patientName;
    private String fatherName;
    private String email;
    private int age;
    private String gender;
    private String bloodGroup;
    private String address;
    private long contactNo;

    public PatientInfo(int patientID, String patientName, String fatherName, String email, int age, String gender, String bloodGroup, String address, long contactNo) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.fatherName = fatherName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.contactNo = contactNo;
    }

    // Getters for patient information
    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public long getContactNo() {
        return contactNo;
    }
}
