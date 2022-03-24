package behavioral.observer.push;

// Observer pattern - Push model

// Details
// 1. One-to-many relationship model that is used to update the state (notify) all dependent objects
// 2. Facilitates loose-coupling through abstractions
// 3. Can be implemented using either push or pull model
// 4. In case of many subjects and few observers storing subject separately can be expensive
// 5. Clients can potentially overwhelm the observers with many small updates thus may require batching the incoming changes
// 6. Change Manager is often added in between the subject and the observer in case of complex dependencies between the two

// Components
// 1. Subject
// 2. Observer
// 3. Concrete Subject
// 4. Concrete Observer
// 5. Client

// Implementations
// 1. Push model (top-down, push from the subject to the observer)
// 2. Pull model (bottom-up, pull from the subject to the observer)

// Examples in JDK
// 1. java.util.EventListener

import java.util.ArrayList;
import java.util.List;

// 5. Client
public class ObserverPattern {
  public static void main(String[] args) {
    ControlTower controlTower = new ControlTower();
    F16 f16A = new F16("A", controlTower);
    F16 f16B = new F16("B", controlTower);
    F16 f16C = new F16("C", controlTower);
    controlTower.notifyObservers(1); // 3 subscribers notified
    F16 f16D = new F16("D", controlTower);
    controlTower.notifyObservers(2); // 4 subscribers notified
    f16B.land();
    f16C.land();
    controlTower.notifyObservers(3); // 2 subscribers notified
  }
}

// 1. Subject
interface Subject {
  void addObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers(Integer state);
}

// 2. Observer
interface Observer { void update(Object newState); }

// 3. Concrete Subject
class ControlTower implements Subject {
  private final List<Observer> observers = new ArrayList<>();
  private Integer state;
  @Override public void addObserver(Observer observer) { observers.add(observer); }
  @Override public void removeObserver(Observer observer) { observers.remove(observer); }
  @Override public void notifyObservers(Integer state) { // can be scheduled to run continuously
    this.state = state;
    for (Observer observer : observers) observer.update(state);
  }
}

interface Aircraft {
  void fly();
  void land();
}

// 4. Concrete Observer
class F16 implements Observer, Aircraft {
  private final String name;
  private final Subject observable;
  public F16(String name, Subject observable) {
    this.name = name;
    this.observable = observable;
    observable.addObserver(this); // subscribe (push model)
  }
  @Override public void update(Object newState) { System.out.println(newState + "-" + name); }
  @Override public void fly() {}
  @Override public void land() { observable.removeObserver(this); } // unsubscribe
}
