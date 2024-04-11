package com.mycompany.testing_project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserAuthentication {
    private static final String FILE_PATH = "users.txt";

    public static boolean authenticate(String username, String password) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        reader.close();
                        return true; // Authentication successful
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Authentication failed
    }
}
