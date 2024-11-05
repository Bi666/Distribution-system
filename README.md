# Distributed Systems Coursework 2

![Language](https://img.shields.io/badge/Language-Java-blue) ![Azure](https://img.shields.io/badge/Cloud-Azure-blue)

This README provides an overview of the implementation of three tasks aimed at creating a distributed system based on serverless computing using Microsoft Azure.

### Module: Distributed Systems (XJCO3211)

This project involves the creation of a distributed system using serverless functions on Azure to simulate an IoT network that gathers and processes environmental data. The tasks are divided into three main parts: simulated data collection, statistical analysis, and setting up a realistic scenario with automation.

## Tasks

### Task 1: Simulated Data Collection

Collect environmental data from 20 sensors using an Azure Function with HTTP trigger. The data is stored in an Azure SQL database for further analysis.

### Task 2: Data Statistics

Use an Azure Function with an SQL trigger to monitor changes in the Azure SQL database storing sensor data, and analyze the sensor data to calculate the minimum, maximum, and average values. The results help identify abnormal environmental conditions.

### Task 3: Realistic Scenario Implementation

Automate data collection and analysis using a combination of time trigger and SQL trigger in Azure Functions. This ensures new data automatically triggers statistical analysis.

## Azure Setup

The solution utilizes the following Azure services:

- **Azure SQL Server**: distributed-csw2.
- **Azure SQL Database**: SensorData (distributed-csw2/SensorData).

- **Azure Functions**

  - **csw2-task1**: HTTP trigger.
  - **csw2-task2**: SQL trigger.
  - **csw2-task3**: Time trigger + SQL trigger.

- **Application Insights****

  - **csw2-task1**: Monitoring Task 1.
  - **csw2-task2**: Monitoring Task 2.

  - **csw2-task3**: Monitoring Task 3.

## Authors
- Biliu Wang
