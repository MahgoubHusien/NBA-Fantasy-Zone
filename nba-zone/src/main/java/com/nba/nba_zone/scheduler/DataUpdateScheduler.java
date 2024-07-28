package com.nba.nba_zone.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataUpdateScheduler {

    // Schedule the task to run every hour
    @Scheduled(fixedRate = 3600000) // 3600000 milliseconds = 1 hour
    public void runPythonScript() {
        try {
            // Path to the Python script
            String scriptPath = "./data/players.py";

            // Command to execute the Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath);

            // Redirect error stream to the standard output
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Replace with logging if needed
                }
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python script failed with exit code: " + exitCode);
            } else {
                System.out.println("Python script executed successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with logging if needed
        }
    }
}
