package functional.statistic;

import functional.functionalClasses.Buffer;

public class BufferStatistic {
  private int numOfPlace;
  private int numOfSource;
  private double timeOfGeneration;

  public BufferStatistic(int numOfPlace, int numOfSource, double timeOfGeneration){
    this.numOfPlace = numOfPlace;
    this.numOfSource = numOfSource;
    this.timeOfGeneration = timeOfGeneration;
  }

  public int getNumOfSource() {
    return numOfSource;
  }

  public int getNumOfPlace() {
    return numOfPlace;
  }

  public double getTimeOfGeneration() {
    return timeOfGeneration;
  }
}
