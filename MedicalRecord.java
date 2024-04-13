
package com.mycompany.testing_project;

public class MedicalRecord {
    private int ID;
    private String bloodPressure;
    private String bloodSugar;
    private String cholesterol;
    private String bloodType;
    private String allergies;

    // Constructor
    public MedicalRecord(int ID, String bloodPressure, String bloodSugar, String cholesterol, String bloodType, String allergies) {
        this.ID = ID;
        this.bloodPressure = bloodPressure;
        this.bloodSugar = bloodSugar;
        this.cholesterol = cholesterol;
        this.bloodType = bloodType;
        this.allergies = allergies;
    }

    // Getters and setters (if needed)
   public int getID() {
        return ID;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getAllergies() {
        return allergies;
    }
}
