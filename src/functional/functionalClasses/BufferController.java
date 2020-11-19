package functional.functionalClasses;

public class BufferController {
  private Buffer buffer;
  private Device[] devices;
  public PointerOnCurrentDevice pointerOnCurrentDevice;

  public BufferController(Buffer buff, Device[] dev, PointerOnCurrentDevice ptrOnCurrDev){
    this.buffer = buff;
    this.devices = dev;
    this.pointerOnCurrentDevice = ptrOnCurrDev;
  }

  public boolean trySendRequestToDevice(){
    int startPos = pointerOnCurrentDevice.value;
    if(buffer.isEmpty()){
      return false;
    }
    do{
      if(!devices[pointerOnCurrentDevice.value].isWorking()){
        devices[pointerOnCurrentDevice.value].getRequest(buffer.giveOutRequest());
        return true;
      }
      pointerOnCurrentDevice.value++;
      if(pointerOnCurrentDevice.value == devices.length) {
        pointerOnCurrentDevice.value = 0;
      }
    } while (pointerOnCurrentDevice.value != startPos);
    return false;
  }

}