package com;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Car {
	private int x;
	private int y;
	public int carNumber;
	private boolean isBusy;
	private List<Integer> takenTrips = new LinkedList<>();

	public Car(int carNumber) {
		this.carNumber =carNumber;
		x = 0;
		y = 0;
		isBusy = false;
	}

	public void tripFinished(Trip trip){
		takenTrips.add(trip.getNumber());
	}

	public int distanceToStart(Trip trip){
		return Math.abs(x - trip.getBeginX()) + Math.abs(y - trip.getBeginY());
	}

	public int earliestFinish(Trip trip){
		return Math.abs(x - trip.getBeginX()) + Math.abs(y - trip.getBeginY()) + trip.getDuration();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean busy) {
		isBusy = busy;
	}

	public List<Integer> getFinishedTrips() {
		return takenTrips;
	}

	public void findClosesTripAndAssign(List<Trip> trips, int finalCurrIteration) {

		final Trip closestTrip = trips.parallelStream()
				.filter(trip -> trip.isTripOptimal(finalCurrIteration, this))
				.min(Comparator.comparingInt(this::earliestFinish))
				.orElse(null);

		if(closestTrip!=null) {
			closestTrip.assignCarToTrip(this);
		}

	}

	public String getFinishedTripsString() {
		return takenTrips.stream().map(Object::toString)
				.collect(Collectors.joining(" "));

	}
}
