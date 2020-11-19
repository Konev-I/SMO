package functional.functionalClasses;

import functional.Simulator;

import static java.lang.Math.*;

public class Device {
  private Request processedRequest;
  private int numOfDevice;
  private double timeOfWorking;
  private double lambda;
  private double stopWorkingTime = 0.0;
  private double freeTime = 0.0;
  private boolean isWorking;

  public Device(int num, double lambda){
    this.numOfDevice = num;
    this.timeOfWorking = 0;
    this.lambda = lambda;
  }

  private void calculateWorkingTime(){
    timeOfWorking = Simulator.getCurrentTime() + lambda * exp((int)(Math.random() * -lambda));
  }

  public void getRequest(Request request){
    if(stopWorkingTime > 0){
      freeTime += Simulator.getCurrentTime() - stopWorkingTime;
    }
    processedRequest = request;
    processedRequest.setTimeOfGetInDevice(Simulator.getCurrentTime());
    calculateWorkingTime();
    isWorking = true;
  }

  public void releaseRequest(){
    stopWorkingTime = Simulator.getCurrentTime();
    processedRequest.setTimeOfRelease(Simulator.getCurrentTime());
    processedRequest = null;
    isWorking = false;
  }

  public double getTimeOfWorking() {
    return timeOfWorking;
  }

  public int getNumOfDevice(){
    return numOfDevice;
  }

  public boolean isWorking(){
    return isWorking;
  }

  public double getFreeTime() {
    return freeTime;
  }
}
