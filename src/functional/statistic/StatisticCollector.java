package functional.statistic;

import functional.Simulator;
import functional.functionalClasses.Buffer;
import functional.functionalClasses.Device;
import functional.functionalClasses.Event;
import functional.functionalClasses.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class StatisticCollector {

  static ArrayList<Request> requests = new ArrayList<>();
  static ArrayList<Device> devices = new ArrayList<>();
  static Buffer buffer;
  static Event event;

  public static void collectEvent(Event e){
    event = e;
  }

  public static Event getEvent(){
    return event;
  }

  public static void collectBuffer(Buffer b){
    buffer = b;
  }

  public static void clearBuffer(){
    buffer = null;
  }

  public static void collectDevices(Device d){
    devices.add(d);
  }

  public static void clearDevices(){
    devices.clear();
  }

  public static void collectRequest(Request r){
    requests.add(r);
  }

  public static void clearRequest(){
    requests.clear();
  }



  public static ObservableList<RequestStatistic> calculateRequestStatistic(){
    RequestStatistic[] statistic = new RequestStatistic[Simulator.getNumOfSources()];
    for(int j = 0; j < Simulator.getNumOfSources(); j++){
      statistic[j] = new RequestStatistic(j);
    }
    for (Request request : requests){
      statistic[request.getNumOfSource()].numOfRequest++;
      if(request.checkRejected()){
        statistic[request.getNumOfSource()].rejected++;
      }
      else if(request.getTimeOfRelease() > 0 && request.getTimeOfGetInDevice() > 0){
        statistic[request.getNumOfSource()].complete++;
        statistic[request.getNumOfSource()].averageInSystem += request.getTimeOfRelease()
        - request.getTimeOfGeneration();
        if(request.getTimeOfGetInBuffer() > 0) {
          statistic[request.getNumOfSource()].averageWaiting += request.getTimeOfGetInDevice()
          - request.getTimeOfGetInBuffer();
          statistic[request.getNumOfSource()].dispWaiting += Math.pow(request.getTimeOfGetInDevice()
          - request.getTimeOfGetInBuffer() - statistic[request.getNumOfSource()].calculateAverageWaiting(), 2);
        }
        statistic[request.getNumOfSource()].averageService += request.getTimeOfRelease()
        - request.getTimeOfGetInDevice();
        statistic[request.getNumOfSource()].dispService += Math.pow(request.getTimeOfRelease()
        - request.getTimeOfGetInDevice() - statistic[request.getNumOfSource()].calculateAverageService(), 2);
      }
    }
    ObservableList<RequestStatistic> reqData = FXCollections.observableArrayList();
    for (int j = 0; j < Simulator.getNumOfSources(); j++){
      statistic[j].probOfReject = (double) statistic[j].rejected / statistic[j].numOfRequest;
      statistic[j].averageInSystem = statistic[j].averageInSystem / statistic[j].numOfRequest;
      statistic[j].averageWaiting = statistic[j].calculateAverageWaiting();
      statistic[j].averageService = statistic[j].calculateAverageService();
      statistic[j].dispWaiting = statistic[j].dispWaiting/statistic[j].numOfRequest;
      statistic[j].dispService = statistic[j].dispService/statistic[j].numOfRequest;
      reqData.add(statistic[j]);
    }
    return reqData;
  }

  public static ObservableList<DeviceStatistic> calculateDeviceStatistic(){
    DeviceStatistic[] statistic = new DeviceStatistic[Simulator.getNumOfDevices()];
    for(int j = 0; j < Simulator.getNumOfDevices(); j++){
      statistic[j] = new DeviceStatistic(j);
    }
    ObservableList<DeviceStatistic> devData = FXCollections.observableArrayList();
    for(Device device: devices){
      statistic[device.getNumOfDevice()].usingFactor = 1 - device.getFreeTime() / Simulator.getFinalTime();
      statistic[device.getNumOfDevice()].isWorking = device.isWorking();
      devData.add(statistic[device.getNumOfDevice()]);
    }
    return devData;
  }

  public static ObservableList<BufferStatistic> calculateBufferStatistic(){
    ObservableList<BufferStatistic> bufData = FXCollections.observableArrayList();
    for(int j = 0; j < buffer.getEmptyPlace(); j++){
      bufData.add(new BufferStatistic(j, buffer.getQueue()[j].getNumOfSource(), buffer.getQueue()[j].getTimeOfGeneration()));
    }
    return bufData;
  }

}
