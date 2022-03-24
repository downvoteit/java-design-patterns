package behavioral.observer.pull;

// Observer pattern - Pull model

import java.util.ArrayList;
import java.util.List;

// Client
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
  Number getState();
}

// 2. Observer
interface Observer { void update(); }

// 3. Concrete Subject
class ControlTower implements Subject {
  private final List<Observer> observers = new ArrayList<>();
  private Integer state;
  @Override public void addObserver(Observer observer) { observers.add(observer); }
  @Override public void removeObserver(Observer observer) { observers.remove(observer); }
  @Override public void notifyObservers(Integer state) { // can be scheduled to run continuously
    this.state = state;
    for (Observer observer : observers) observer.update(); 
  }
  public Integer getState() { return state; }
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
  @Override public void update() { System.out.println(observable.getState() + "-" + name); }
  @Override public void fly() {}
  @Override public void land() { observable.removeObserver(this); } // unsubscribe
}
