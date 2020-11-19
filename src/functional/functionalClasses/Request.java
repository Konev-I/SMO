package functional.functionalClasses;

public class Request {
  private int numOfSource;
  private double timeOfGeneration;
  private double timeOfGetInDevice = 0;
  private double timeOfGetInBuffer = 0;
  private double timeOfRelease = 0;
  private boolean isRejected;

  public Request(int num, double time){
    this.numOfSource = num;
    this.timeOfGeneration = time;
  }

  public boolean checkRejected(){
    return isRejected;
  }

  public void setRejected(){
    isRejected = true;
  }

  public void setTimeOfRelease(double timeOfRelease) {
    this.timeOfRelease = timeOfRelease;
  }

  public void setTimeOfGetInDevice(double timeOfGetInDevice) {
    this.timeOfGetInDevice = timeOfGetInDevice;
  }

  public void setTimeOfGetInBuffer(double timeOfGetInBuffer) {
    this.timeOfGetInBuffer = timeOfGetInBuffer;
  }

  public double getTimeOfGetInDevice() {
    return timeOfGetInDevice;
  }

  public double getTimeOfGetInBuffer() {
    return timeOfGetInBuffer;
  }

  public double getTimeOfRelease(){
    return timeOfRelease;
  }

  public double getTimeOfGeneration() {
    return timeOfGeneration;
  }

  public int getNumOfSource() {
    return numOfSource;
  }
}
