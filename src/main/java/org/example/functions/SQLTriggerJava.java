package org.example.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.sql.annotation.SQLTrigger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLTriggerJava {
    @FunctionName("SQLTriggerFunction")
    public void run(
            @SQLTrigger(name = "sqlTrigger", tableName = "SensorData", connectionStringSetting = "AzureSQLConnectionString") String content,
            final ExecutionContext context) {
        context.getLogger().info("SQL trigger executed.");

        String jdbcConnectionString = System.getenv("JDBC_SQLConnectionString");

        try (Connection connection = DriverManager.getConnection(jdbcConnectionString);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MIN(temperature), MAX(temperature), AVG(temperature), MIN(wind_speed), MAX(wind_speed), AVG(wind_speed), MIN(humidity), MAX(humidity), AVG(humidity), MIN(co2_level), MAX(co2_level), AVG(co2_level) FROM SensorData")) {

            if (resultSet.next()) {
                int minTemp = resultSet.getInt(1);
                int maxTemp = resultSet.getInt(2);
                double avgTemp = resultSet.getDouble(3);

                int minWindSpeed = resultSet.getInt(4);
                int maxWindSpeed = resultSet.getInt(5);
                double avgWindSpeed = resultSet.getDouble(6);

                int minHumidity = resultSet.getInt(7);
                int maxHumidity = resultSet.getInt(8);
                double avgHumidity = resultSet.getDouble(9);

                int minCO2 = resultSet.getInt(10);
                int maxCO2 = resultSet.getInt(11);
                double avgCO2 = resultSet.getDouble(12);

                context.getLogger().info(String.format("Temperature - Min: %d, Max: %d, Avg: %.2f", minTemp, maxTemp, avgTemp));
                context.getLogger().info(String.format("Wind Speed - Min: %d, Max: %d, Avg: %.2f", minWindSpeed, maxWindSpeed, avgWindSpeed));
                context.getLogger().info(String.format("Humidity - Min: %d, Max: %d, Avg: %.2f", minHumidity, maxHumidity, avgHumidity));
                context.getLogger().info(String.format("CO2 Level - Min: %d, Max: %d, Avg: %.2f", minCO2, maxCO2, avgCO2));
            }
        } catch (Exception e) {
            context.getLogger().severe("Error retrieving data: " + e.getMessage());
        }
    }
}
