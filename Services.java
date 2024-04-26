package junitlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//package com.mycompany.testing_project;

import java.util.Date;

public class Services {
    private int serviceId;
    private String serviceName;
    private String serviceDate;
    private int patientId;
    private String patientName;
    private double serviceCharges;
    private int departmentNumber;

    public Services(int serviceId, String serviceName, String serviceDate, int patientId, String patientName, double serviceCharges, int departmentNumber) {
    	
        
      
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDate = serviceDate;
        this.patientId = patientId;
        this.patientName = patientName;
        this.serviceCharges = serviceCharges;
        this.departmentNumber = departmentNumber;
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
    public int getDepartmentNumber(){
        return departmentNumber;
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
    
    public void setdepartmentNumber(int departmentNumber){
    	
        
        this.departmentNumber = departmentNumber;
    }
 // Load services data from a file
    public static ArrayList<Services> loadServicesData(String filePath) {
        ArrayList<Services> services = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] serviceInfo = line.split(",");
                if (serviceInfo.length == 7) {
                    int serviceId = Integer.parseInt(serviceInfo[0]);
                    String serviceName = serviceInfo[1];
                    String serviceDate = serviceInfo[2];
                    int patientId = Integer.parseInt(serviceInfo[3]);
                    String patientName = serviceInfo[4];
                    double serviceCharges = Double.parseDouble(serviceInfo[5]);
                    int departmentNumber = Integer.parseInt(serviceInfo[6]);
                    Services service = new Services(serviceId, serviceName, serviceDate, patientId, patientName, serviceCharges, departmentNumber);
                    services.add(service);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading services data: " + ((Throwable) e).getMessage());
        }
        return services;
    }

    // Save services data to a file
    public static void saveToFile(ArrayList<Services> services, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Services service : services) {
                writer.write(service.getServiceId() + "," + service.getServiceName() + "," + service.getServiceDate() + ","
                        + service.getPatientId() + "," + service.getPatientName() + "," + service.getServiceCharges() + ","
                        + service.getDepartmentNumber() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving services data: " + e.getMessage());
        }
    }
}

