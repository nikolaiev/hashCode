package com;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileResultWriter {
    private final String fileName;
    private final List<Car> cars;

    public FileResultWriter(String s, List<Car> cars) {
        this.fileName = s;
        this.cars = cars;
    }

    public void writeFile() throws IOException {

        Path fileToDeletePath = Paths.get(fileName);
        Files.delete(fileToDeletePath);

        try (PrintWriter f0 = new PrintWriter(new FileWriter(fileName))) {

            cars.forEach(car ->
                    f0.append(String.valueOf(car.getFinishedTrips().size()))
                            .append(" ")
                            .append(car.getFinishedTripsString())
                            .append("\n"));
        }
    }
}
