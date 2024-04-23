public class Departments {
    private String departmentName;
    private int departmentNumber;
    //private String location;
    // Add other relevant attributes and methods as needed

    public Departments(String departmentName, int departmentNumber) {
    	setDepartmentName(departmentName);
    	setDepartmentNumber(departmentNumber);
        //this.location = location;
    }

    // Getters and setters for department details
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        if (isValidDepartmentName(departmentName)) {
            this.departmentName = departmentName;
        } else {
            throw new IllegalArgumentException("Invalid department name: " + departmentName);
        }
    }

    private boolean isValidDepartmentName(String departmentName) {
        return departmentName != null && !departmentName.trim().isEmpty() && departmentName.matches("[a-zA-Z\\s]+");
    }

    public void setDepartmentNumber(int departmentNumber) {
    	 if (departmentNumber >= 0) {
             this.departmentNumber = departmentNumber;
         } else {
             // If department number is negative, set default value (or ignore)
             this.departmentNumber = 0; // Defaulting to 0
             throw new IllegalArgumentException("Invalid department number: " + departmentNumber);

         }
    }

    public int getdepartmentNumber() {
        return departmentNumber;
    }
}
