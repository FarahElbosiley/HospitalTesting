package junitlab;
import java.io.*;
import java.util.ArrayList;

public class Appointments {
    private int appointmentId;
    private String doctorName;
    private String departmentName;

    public Appointments(int appointmentId, String doctorName, String departmentName) {
    	
    	if (appointmentId < 0) {
            	
                throw new IllegalArgumentException("ID must be a non-negative integer.");
            }
            
            if (doctorName.isBlank() || departmentName.isBlank()) {
                throw new IllegalArgumentException("All fields must have non-null values.");
            }
            
          

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

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setDoctorName(String doctorName) {
    	if (doctorName.isBlank()) {
            throw new IllegalArgumentException("All fields must have non-null values.");
        }
        this.doctorName = doctorName;
    }

    public void setDepartmentName(String departmentName) {
    	if (departmentName.isBlank()) {
            throw new IllegalArgumentException("All fields must have non-null values.");
        }
        this.departmentName = departmentName;
    }
    // Load appointments data from file
    public static ArrayList<Appointments> loadAppointmentsData(String filePath) {
        ArrayList<Appointments> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] appointmentInfo = line.split(",");
                if (appointmentInfo.length == 3) {
                    int appointmentId = Integer.parseInt(appointmentInfo[0]);
                    String doctorName = appointmentInfo[1];
                    String departmentName = appointmentInfo[2];
                    Appointments appointment = new Appointments(appointmentId, doctorName, departmentName);
                    appointments.add(appointment);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading appointments data: " + e.getMessage());
        }
        return appointments;
    }

    // Save appointments data to file
    public static void saveToFile(ArrayList<Appointments> appointments, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Appointments appointment : appointments) {
                writer.write(appointment.getAppointmentId() + "," + appointment.getDoctorName() + "," + appointment.getDepartmentName() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments data: " + e.getMessage());
        }
    }
}
