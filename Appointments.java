package com.mycompany.testing_project;

import java.io.*;
import java.util.ArrayList;

public class Appointments {
    private int appointmentId;
    private String doctorName;
    private String departmentName;

    public Appointments(int appointmentId, String doctorName, String departmentName) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.departmentName = departmentName;
    }

    // Getter methods
    public int getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

   
   
}
