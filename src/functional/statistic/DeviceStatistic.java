package functional.statistic;

public class DeviceStatistic {
  public int numOfDevice;
  public double usingFactor;
  public boolean isWorking;

  public DeviceStatistic(int numOfDevice){
    this.numOfDevice = numOfDevice;
  }

  public double getUsingFactor() {
    return usingFactor;
  }

  public int getNumOfDevice() {
    return numOfDevice;
  }

  public boolean getIsWorking(){
    return isWorking;
  }
}
