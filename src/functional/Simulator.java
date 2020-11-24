package functional;

import functional.functionalClasses.*;
import functional.statistic.StatisticCollector;

import java.util.Comparator;
import java.util.TreeMap;

public class Simulator {
    private static int numOfSources = 5;
    private static int numOfDevices = 3;
    private static int sizeOfQueue = 5;
    private static double currentTime = 0;
    private static double finalTime = 1000;
    private static double alpha = 0.1;
    private static double beta = 10;
    private static double lambda = 2;

    private static Device[] devices;
    private static Buffer buffer;
    private static PointerOnCurrentDevice pointerOnCurrentDevice;
    private static RequestController requestController;
    private static BufferController bufferController;
    private static Source[] sources;
    private static TreeMap<Double, Event> mapOfEvents;

  public static int getNumOfSources() {
    return numOfSources;
  }

  public static int getNumOfDevices() {
    return numOfDevices;
  }

  public static int getSizeOfQueue() {
    return sizeOfQueue;
  }

  public static double getCurrentTime(){
    return currentTime;
  }

  public static double getFinalTime() {
    return finalTime;
  }

  public static double getAlpha() {
    return alpha;
  }

  public static double getBeta() {
    return beta;
  }

  public static double getLambda() {
    return lambda;
  }

  public static PointerOnCurrentDevice getPointerOnCurrentDevice(){
    return pointerOnCurrentDevice;
  }

  public static void setParameters(int numOfSources_, int numOfDevices_, int sizeOfQueue_, double alpha_, double beta_,
                                    double lambda_, double finalTime_) {
      numOfSources = numOfSources_;
      numOfDevices = numOfDevices_;
      sizeOfQueue = sizeOfQueue_;
      alpha = alpha_;
      beta = beta_;
      lambda = lambda_;
      finalTime = finalTime_;
    }

  public static void runSimulation(int stage) {
    if(stage == 0) {
      currentTime = 0;
      StatisticCollector.clearRequest();
      StatisticCollector.clearDevices();
      StatisticCollector.clearBuffer();
      devices = new Device[numOfDevices];
      for (int i = 0; i < numOfDevices; i++) {
        devices[i] = new Device(i, lambda);
        StatisticCollector.collectDevices(devices[i]);
      }

      buffer = new Buffer(sizeOfQueue);
      StatisticCollector.collectBuffer(buffer);

      pointerOnCurrentDevice = new PointerOnCurrentDevice(0);

      requestController = new RequestController(buffer, devices, pointerOnCurrentDevice);
      bufferController = new BufferController(buffer, devices, pointerOnCurrentDevice);

      sources = new Source[numOfSources];

      for (int i = 0; i < numOfSources; i++) {
        sources[i] = new Source(i, requestController, alpha, beta);
      }

      mapOfEvents = new TreeMap<Double, Event>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
          if (o1 > o2) {
            return 1;
          }
          else {
            if (o1.equals(o2)) {
              return 0;
            }
            return -1;
          }
        }
      });

      for (int i = 0; i < sources.length; i++) {
        sources[i].calculateGenerationTime(0);
        mapOfEvents.put(sources[i].getTimeOfGeneration(), new Event(Event.TypesOfEvent.REQUEST_GENERATED, i));
      }
    }
    else if(stage == 1) {
      while (currentTime < finalTime) {
        step();
      }
    }

    else if(stage == 2) {
      if (currentTime < finalTime) {
        step();
      }
    }
  }

  private static void step() {
    Event currentEvent;
    currentEvent = mapOfEvents.higherEntry(currentTime).getValue();
    StatisticCollector.collectEvent(currentEvent);
    currentTime = mapOfEvents.higherKey(currentTime);
    if (currentEvent.getTypeOfEvent() == Event.TypesOfEvent.REQUEST_GENERATED) {
      sources[currentEvent.getSourceOfEvent()].generateRequest();
      if (requestController.getLastDestination() != -1) {
        mapOfEvents.put(devices[pointerOnCurrentDevice.value].getTimeOfWorking(),
        new Event(Event.TypesOfEvent.REQUEST_RELEASED, pointerOnCurrentDevice.value));
      }
      sources[currentEvent.getSourceOfEvent()].calculateGenerationTime(currentTime);
      mapOfEvents.put(sources[currentEvent.getSourceOfEvent()].getTimeOfGeneration(),
      new Event(Event.TypesOfEvent.REQUEST_GENERATED, currentEvent.getSourceOfEvent()));
    }
    else {
      devices[currentEvent.getSourceOfEvent()].releaseRequest();
      if (bufferController.trySendRequestToDevice()) {
        mapOfEvents.put(devices[pointerOnCurrentDevice.value].getTimeOfWorking(),
        new Event(Event.TypesOfEvent.REQUEST_RELEASED, pointerOnCurrentDevice.value));
      }
    }
  }
}
