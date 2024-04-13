package com.mycompany.testing_project;

import java.util.Date;

public class Service {
    private int serviceId;
    private String serviceName;
    private Date serviceDate;
    private int patientId;
    private String patientName;
    private double serviceCharges;

    public Service(int serviceId, String serviceName, Date serviceDate, int patientId, String patientName, double serviceCharges) {
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

    public Date getServiceDate() {
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
}
