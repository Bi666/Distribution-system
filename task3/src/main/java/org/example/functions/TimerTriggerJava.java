package org.example.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class TimerTriggerJava {
    @FunctionName("TimerTriggerFunction")
    public void run(
            @TimerTrigger(name = "timerInfo", schedule = "*/5 * * * * *") String timerInfo,
            final ExecutionContext context) {
        context.getLogger().info("Timer trigger executed at: " + java.time.LocalDateTime.now());

        // Generate data from 20 sensors
        String JDBCconnectString = System.getenv("JDBC_SQLConnectionString");
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            int temperature = 8 + random.nextInt(8);
            int windSpeed = 15 + random.nextInt(11);
            int humidity = 40 + random.nextInt(31);
            int co2 = 500 + random.nextInt(1001);

            // Insert data into the SensorData table
            try (Connection connection = DriverManager.getConnection(JDBCconnectString);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO SensorData (sensor_id, temperature, wind_speed, humidity, co2_level) VALUES (?, ?, ?, ?, ?)")) {
                statement.setInt(1, i);
                statement.setInt(2, temperature);
                statement.setInt(3, windSpeed);
                statement.setInt(4, humidity);
                statement.setInt(5, co2);
                statement.executeUpdate();
            } catch (Exception e) {
                context.getLogger().severe("Error inserting data: " + e.getMessage());
            }
        }
    }
}