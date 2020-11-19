package functional.functionalClasses;

public class RequestController {
  private Buffer buffer;
  private Device[] devices;
  private PointerOnCurrentDevice pointerOnCurrentDevice;
  private int lastDestination;


  public RequestController(Buffer buff, Device[] dev, PointerOnCurrentDevice pointerOnCurrDev){
    this.buffer = buff;
    this.devices = dev;
    this.pointerOnCurrentDevice = pointerOnCurrDev;
  }

  public int getLastDestination() {
    return lastDestination;
  }

  public boolean checkFreeDevice(){
    int startPos = pointerOnCurrentDevice.value;
    do{
      if(!devices[pointerOnCurrentDevice.value].isWorking()){
        return true;
      }
      pointerOnCurrentDevice.value++;
      if(pointerOnCurrentDevice.value == devices.length){
        pointerOnCurrentDevice.value = 0;
      }
    } while (pointerOnCurrentDevice.value != startPos);
    return false;
  }

  public void chooseDestinationOfRequest(Request request){
    if(buffer.isEmpty()){
      if(!checkFreeDevice()){
        buffer.putInBuffer(request);
        lastDestination = -1;
      }
      else{
        devices[pointerOnCurrentDevice.value].getRequest(request);
        lastDestination = pointerOnCurrentDevice.value;
      }
    }
    else{
      buffer.putInBuffer(request);
      lastDestination = -1;
    }
  }

}
