package com;

import static com.Runner.ITERATION;

public class Trip {
	private int beginX;
	private int beginY;
	private int endX;
	private int endY;
	private int earliestStart;
	private int latestFinish;
	private boolean isTaken;
	private boolean isOver=false;
	private int finishCounter;
	private Integer number;
	private Car car;


	public Trip(int beginX, int beginY, int endX, int endY, int earliestStart, int latestFinish, int number) {
		this.beginX = beginX;
		this.beginY = beginY;
		this.endX = endX;
		this.endY = endY;
		this.earliestStart = earliestStart;
		this.latestFinish = latestFinish;
		this.isTaken = false;
		this.number=number;
	}

	public int getDuration(){
		return Math.abs(beginX-endX)+Math.abs(beginY-endY);
	}

	public boolean isOver(){
		return isOver;
	}

	public boolean isTakable(int iterationNumber){
		return !isOver && iterationNumber >= earliestStart;
	}

	public boolean isFinishableAfterCarCame(Car car, int interation){
		return !isOver && (interation + car.distanceToStart(this)) + getDuration() <= latestFinish;
	}

	public boolean isSkipable(int iterationNumber){
		return (iterationNumber + getDuration()) > latestFinish;
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public void setOver(boolean over) {
		isOver = over;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean taken) {
		isTaken = taken;
	}

	public int getEarliestStart() {
		return earliestStart;
	}

	public void setEarliestStart(int earliestStart) {
		this.earliestStart = earliestStart;
	}

	public int getLatestFinish() {
		return latestFinish;
	}

	public void setLatestFinish(int latestFinish) {
		this.latestFinish = latestFinish;
	}

	public Integer getNumber() {
		return number;
	}

	public void assignCarToTrip(Car car) {
			this.setTaken(true);
			this.car = car;
			this.car.setBusy(true);
			finishCounter = this.car.distanceToStart(this) + getDuration();
	}

	public void decreaseFinishCounter() {
		--finishCounter;
		if(finishCounter==0){
			isOver = true;
			this.car.setBusy(false);
			this.car.tripFinished(this);
			this.car.setX(this.getEndX());
			this.car.setY(this.getEndY());
		}
	}
}
