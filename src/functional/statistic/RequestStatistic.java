package functional.statistic;

public class RequestStatistic {
  public int numOfSource;
  public int numOfRequest = 0;
  public int rejected = 0;
  public int complete = 0;
  public double probOfReject = 0;
  public double averageInSystem = 0;
  public double averageWaiting = 0;
  public double averageService = 0;
  public double dispWaiting = 0;
  public double dispService = 0;

  public RequestStatistic(int numOfSource){
    this.numOfSource = numOfSource;
  }

  public int getNumOfSource() {
    return numOfSource;
  }

  public double getAverageInSystem() {
    return averageInSystem;
  }

  public double getAverageService() {
    return averageService;
  }

  public double getAverageWaiting() {
    return averageWaiting;
  }

  public double getDispService() {
    return dispService;
  }

  public double getDispWaiting() {
    return dispWaiting;
  }

  public double getProbOfReject() {
    return probOfReject;
  }

  public int getComplete() {
    return complete;
  }

  public int getNumOfRequest() {
    return numOfRequest;
  }

  public int getRejected() {
    return rejected;
  }

  public double calculateAverageWaiting(){
    return averageWaiting/numOfRequest;
  }

  public double calculateAverageService(){
    return averageService/numOfRequest;
  }
}
