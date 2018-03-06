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
			if(currIteration%1000 == 0)
				System.out.println("ITERATION50 " + currIteration+ " from "+ITERATION);

			int finalCurrIteration = currIteration;

			trips.parallelStream().filter(t->!t.isOver()).filter(Trip::isTaken)
					.forEach(Trip::decreaseFinishCounter);

			if(currIteration%10 == 0)
				trips.removeIf(Trip::isOver);

			cars.stream().filter(car -> !car.isBusy())
					.forEach(car -> car.findClosesTripAndAssign(trips,finalCurrIteration));

		}

		cars.forEach(car -> System.out.println(car.getFinishedTrips()));

		System.out.println("Still have " + trips.size());

		FileResultWriter fileWriter = new FileResultWriter("result50.txt",cars);
		fileWriter.writeFile();
	}
}
