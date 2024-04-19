package com.mycompany.testing_project;

import java.util.Date;

public class Service {
    private int serviceId;
    private String serviceName;
    private String serviceDate;
    private int patientId;
    private String patientName;
    private double serviceCharges;

    public Service(int serviceId, String serviceName, String patientName, int patientId, String patientName1, double serviceCharges) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDate = serviceDate;
        this.patientId = patientId;
        this.patientName = patientName;
        this.serviceCharges = serviceCharges;
    }

    // Getter methods
    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getServiceCharges() {
        return serviceCharges;
    }


    public void setServiceId(int serviceId) {
    this.serviceId = serviceId;
}

public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
}

public void setServiceDate(String serviceDate) {
    this.serviceDate = serviceDate;
}

public void setPatientId(int patientId) {
    this.patientId = patientId;
}

public void setPatientName(String patientName) {
    this.patientName = patientName;
}

public void setServiceCharges(double serviceCharges) {
    this.serviceCharges = serviceCharges;
}

}
