package com;

import static com.FileParser.middle;
import static com.FileParser.width;

public class Trip {
    private int beginX;
    private int beginY;
    private int endX;
    private int endY;
    private int earliestStart;
    private int latestFinish;
    private boolean isTaken;
    private boolean isOver;
    private int finishCounter;
    private Integer number;
    private Car car;
    private int duration = -1;


    public Trip(int beginX, int beginY, int endX, int endY, int earliestStart, int latestFinish, int number) {
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
        this.endY = endY;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
        this.isTaken = false;
        this.number = number;
        this.isOver = false;
    }

    public boolean isOver() {
        return isOver;
    }

    public boolean  isTripOptimal(int iterationNumber, Car car) {
        if(isOver() || isTaken())
            return false;

        int distanceToStart = car.distanceToStart(this);

        return  distanceToStart<50
                &&  isTakableWhenCarCame(distanceToStart, iterationNumber)
                && isFinishableAfterCarCame(distanceToStart, iterationNumber);
    }

    private boolean isTakableWhenCarCame(int distanceToStart, int iterationNumber) {
        return (iterationNumber + distanceToStart) >= earliestStart;
    }

    private boolean isFinishableAfterCarCame(int distanceToStart, int interation) {
        return distanceToStart + getDuration() <= latestFinish - interation;
    }

    public int getDuration() {
        if (duration == -1)
            duration = Math.abs(beginX - endX) + Math.abs(beginY - endY);
        return duration;
    }

    public boolean isSkipable(int iterationNumber) {
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

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
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

    public int getLatestFinish() {
        return latestFinish;
    }

    public Integer getNumber() {
        return number;
    }

    public synchronized  void assignCarToTrip(Car car) {
        this.setTaken(true);
        this.car = car;
        this.car.setBusy(true);
        finishCounter = this.car.distanceToStart(this) + getDuration();
    }

    public synchronized  void decreaseFinishCounter() {
        --finishCounter;
        if (finishCounter == 0) {
            isOver = true;
            this.car.setBusy(false);
            this.car.tripFinished(this);
            this.car.setX(this.getEndX());
            this.car.setY(this.getEndY());
        }
    }
}
