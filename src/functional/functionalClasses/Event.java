package functional.functionalClasses;

public class Event {
    public enum TypesOfEvent {REQUEST_GENERATED, REQUEST_RELEASED};

  private TypesOfEvent typeOfEvent;
  private int sourceOfEvent;

  public Event(TypesOfEvent type, int source){
    this.typeOfEvent = type;
    this.sourceOfEvent = source;
  }

  public TypesOfEvent getTypeOfEvent() {
    return typeOfEvent;
  }

  public int getSourceOfEvent() {
    return sourceOfEvent;
  }

  @Override
  public String toString(){
    if(typeOfEvent == TypesOfEvent.REQUEST_GENERATED){
      return "Генерация заявки";
    }
    else{
      return "Прибор обработал заявку";
    }
  }
}
