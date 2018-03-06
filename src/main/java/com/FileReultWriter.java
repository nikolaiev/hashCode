package com;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReultWriter {
	private final String fileName;
	private final List<Car> cars;

	public FileReultWriter(String s, List<Car> cars) {
		this.fileName = s;
		this.cars=cars;
	}

	public void writeFile() throws IOException {

		Path fileToDeletePath = Paths.get(fileName);
		Files.delete(fileToDeletePath);

		PrintWriter f0 = new PrintWriter(new FileWriter(fileName));

		cars.stream().forEach(car -> {
			f0.append(car.getFinishedTrips().size()+" "+car.getFinishedTripsString()+"\n");
		});

		f0.close();
	}
}
