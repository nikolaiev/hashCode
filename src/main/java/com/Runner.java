package com;

import java.io.IOException;
import java.util.List;

public class Runner {
	public static int ITERATION;
	public static int BONUSES;
	public static List<Trip> trips;
	public static void main(String[] args) throws IOException {
		//TODO args
		final String fileName = "e_high_bonus.in";
		FileParser parser = new FileParser(fileName);

		trips = parser.getTrips();
		final List<Car> cars = parser.getCars();
		ITERATION = parser.getIteration();
		BONUSES = parser.getIteration();

		for(int currIteration = 0; currIteration<=ITERATION;currIteration++){
			System.out.println("ITERATION " + currIteration+ " from "+ITERATION);
			int finalCurrIteration = currIteration;

			trips.stream().filter(t->!t.isOver()).filter(Trip::isTaken)
					.forEach(Trip::decreaseFinishCounter);

			cars.stream().filter(car -> !car.isBusy())
					.forEach(car -> car.findClosesTripAndAssign(trips,finalCurrIteration));

		}

		cars.forEach(car -> {
			System.out.println(car.getFinishedTrips());
		});

		FileReultWriter fileWriter = new FileReultWriter("result.txt",cars);
		fileWriter.writeFile();
	}
}
