package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileParser {

	private List<Trip> trips = new ArrayList<>();
	private List<Car> cars;
	private String fileName;
	public static int iteration;
	private int bonuses;
	//TODO repair shit code
	public static int width;
	public static int height;
	public static int middle;

	public FileParser(String fileName) throws IOException {
		this.fileName = fileName;
		parseFile();
	}

	private void parseFile() throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.map(line -> line.split("\\s")).forEach(arrayString -> {
				final List<Integer> intLine = Arrays.stream(arrayString).map(Integer::valueOf).collect(Collectors.toList());

				if(StateHolder.isFirstLine){
					width = intLine.get(0);
					height = intLine.get(1);
					middle = (width+height)/2;

					final Integer carsCount = intLine.get(2);
					cars = new ArrayList<>(carsCount);

					for(int i=0;i<carsCount;i++){
						cars.add(new Car(i));
					}

					trips = new ArrayList<>(intLine.get(3));
					bonuses = intLine.get(4);
					iteration = intLine.get(5);
					StateHolder.isFirstLine=false;
				} else{
					trips.add(new Trip(intLine.get(0),intLine.get(1),intLine.get(2),intLine.get(3),intLine.get(4),intLine.get(5),
							StateHolder.tripNumber++));
				}
			});
		}
	}

	public List<Trip> getTrips(){
		return trips;
	}

	public List<Car> getCars() {
		return cars;
	}

	public int getIteration() {
		return iteration;
	}

	public int getBonuses() {
		return bonuses;
	}

	private static class StateHolder{
		private StateHolder(){}

		static boolean isFirstLine = true;
		static int tripNumber = 0;
	}
}
