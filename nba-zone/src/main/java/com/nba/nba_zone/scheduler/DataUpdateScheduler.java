package com.nba.nba_zone.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

@Component
public class DataUpdateScheduler {

    // Schedule to run all Python scripts every Monday at midnight
    @Scheduled(cron = "0 0 0 * * MON")
    public void runAllPythonScripts() {
        executePythonScript("./data/players.py");
        executePythonScript("./data/coaches.py");
        executePythonScript("./data/teams.py");
        executePythonScript("./data/scoreboard.py");
        executePythonScript("./data/games.py");
        executePythonScript("./data/standings.py");
        executePythonScript("./data/leagueleaders.py");
    }

    private void executePythonScript(String scriptPath) {
        try (FileWriter fw = new FileWriter("update_data.log", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter logWriter = new PrintWriter(bw)) {

            ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logWriter.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                logWriter.println("Python script failed with exit code: " + exitCode);
            } else {
                logWriter.println("Python script executed successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
