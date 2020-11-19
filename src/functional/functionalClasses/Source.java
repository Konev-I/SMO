package functional.functionalClasses;

import functional.statistic.StatisticCollector;

public class Source {
  private int numOfSource;
  private double timeOfGeneration;
  private RequestController requestController;
  private double alpha;
  private double beta;

  public Source(int num, RequestController reqContr, double alpha, double beta){
    this.numOfSource = num;
    this.timeOfGeneration = 0;
    this.requestController = reqContr;
    this.alpha = alpha;
    this.beta = beta;
  }

  public int getNumOfSource(){
    return numOfSource;
  }

  public double getTimeOfGeneration(){
    return timeOfGeneration;
  }

  public void calculateGenerationTime(double currentTime){
    timeOfGeneration = currentTime + (beta - alpha) * Math.random() + alpha;
  }

  public void generateRequest(){
    Request newRequest = new Request(numOfSource, timeOfGeneration);
    StatisticCollector.collectRequest(newRequest);
    requestController.chooseDestinationOfRequest(newRequest);
  }
}
