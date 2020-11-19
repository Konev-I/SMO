package functional.functionalClasses;

import functional.Simulator;

public class Buffer {
  private int sizeOfQueue;
  private int emptyPlace;
  private Request[] queue;

  public Buffer(int size){
    this.sizeOfQueue = size;
    this.emptyPlace = 0;
    this.queue = new Request[sizeOfQueue];
  }

  public Request[] getQueue(){
    return queue;
  }

  public int getEmptyPlace() {
    return emptyPlace;
  }

  public int numOfRequest(){
    return emptyPlace;
  }

  public void putInBuffer(Request request){
    request.setTimeOfGetInBuffer(Simulator.getCurrentTime());
    if(sizeOfQueue != emptyPlace){
      queue[emptyPlace] = request;
      emptyPlace++;
    }
    else{
      queue[emptyPlace - 1].setRejected();
      queue[emptyPlace - 1] = request;
    }
  }

  public Request giveOutRequest() {
    int numOfRequest = 0;
    int highestPriorityNum = queue[0].getNumOfSource();
    for(int i = 0; i < emptyPlace; i++){
      if(highestPriorityNum > queue[i].getNumOfSource()){
        highestPriorityNum = queue[i].getNumOfSource();
        numOfRequest = i;
      }
      else if(highestPriorityNum == queue[i].getNumOfSource()){
        if(queue[numOfRequest].getTimeOfGeneration() > queue[i].getTimeOfGeneration()){
          numOfRequest = i;
        }
      }
    }
    Request r = queue[numOfRequest];
    for(int i = numOfRequest; i < emptyPlace - 1; i++){
      queue[i] = queue[i+1];
    }
    emptyPlace--;
    queue[emptyPlace] = null;
    return r;
  }

  public boolean isEmpty(){
    return emptyPlace == 0;
  }

  @Override
  public String toString(){
    String output = "";
    for(int i = 0; i < sizeOfQueue; i++){
      if(i < emptyPlace) {
        output += "  *" + i + ": Источник - " + queue[i].getNumOfSource() + ", Время генерации: "
          + queue[i].getTimeOfGeneration() + ";\n";
      }
      else{
        output +="  *" + i + ": null;\n";
      }
    }
    return output;
  }
}
