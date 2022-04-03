package creational.abstractfactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Factory pattern
 *
 * Details
 * 1. Allows an interface to create a family of related products without specifying concrete classes
 * 2. Can produce varying products through object composition as opposed to inheritance
 * 3. Best represented by a singleton object
 * 4. A factory of factories that is easily extensible and flexible
 *
 * Components
 * 1. Abstract Factory
 * 2. Concrete Factory
 * 3. Abstract Product
 * 4. Concrete Product
 * 5. Client
 *
 * Implementations
 * 1. Implement an abstract type to make a concrete factory (factory of factories) then pass it to the client
 *
 * Examples in JDK
 * 1. javax.xml.parsers.DocumentBuilderFactory.newInstance()
 * 2. javax.xml.transform.TransformerFactory.newInstance()
 *
 */

// 5. Client
public class AbstractFactoryPattern {
  public static void main(String[] args) {
    { // Naive creation
      F16Naive f16Naive = new F16Naive();
      f16Naive.main();
    }
    { // Abstracted creation
      F16Abstracted f16Abstracted = new F16Abstracted();
      f16Abstracted.main();
    }
    { // Better abstracted creation
      F16AbstractedFactory f16AbstractedFactory = new F16AbstractedFactory();
      F16Factory f16Factory = new F16Factory();
      f16AbstractedFactory.main(f16Factory);
    }
    { // Abstract Factory
      F16AbstractFactory f16AbstractFactory = new F16AbstractFactory();
      Boeing747AbstractFactory boeing747AbstractFactory = new Boeing747AbstractFactory();
      List<Aircraft> planes = new ArrayList<>();
      planes.add(new Aircraft(f16AbstractFactory));
      planes.add(new Aircraft(boeing747AbstractFactory));
      for (Aircraft aircraft : planes) aircraft.fly();
    }
  }
}

class F16EngineConcrete { public void start() {} }
class F16CockpitConcrete {}
class F16WingsConcrete {}

// Implementations are concrete and exposes new keyword to the consumer
class F16Naive {
  public void main() {
    F16EngineConcrete f16Engine = new F16EngineConcrete(); // exposed concrete implementation to the consumer
    F16CockpitConcrete f16Cockpit = new F16CockpitConcrete(); // exposed concrete implementation to the consumer
    F16WingsConcrete f16Wings = new F16WingsConcrete(); // exposed concrete implementation to the consumer
    List<F16EngineConcrete> engines = new ArrayList<>(); // parametrized type using a concrete class
    engines.add(f16Engine);
    for (F16EngineConcrete engine : engines) engine.start();
  }
}

// 3. Abstract Product
interface Engine { void start(); }
interface Cockpit { }
interface Wings { }

// 4. Concrete Product
class F16Engine implements Engine { @Override public void start() {} }
class F16Cockpit implements Cockpit { }
class F16Wings implements Wings { }
class Boeing747Engine implements Engine { @Override public void start() {} }
class Boeing747Cockpit implements Cockpit { }
class Boeing747Wings implements Wings { }

// Implementations are abstract but still exposes new keyword to the consumer
class F16Abstracted {
  public void main() {
    Engine f16Engine = new F16Engine(); // exposed abstracted implementation to the consumer
    Cockpit f16Cockpit = new F16Cockpit(); // exposed abstracted implementation to the consumer
    Wings f16Wings = new F16Wings(); // exposed abstracted implementation to the consumer
    List<Engine> engines = new ArrayList<>();
    engines.add(f16Engine);
    for (Engine engine : engines) engine.start();
  }
}

// Returns concrete implementations
class F16Factory {
  public Engine createEngine() { return new F16Engine(); }
  public Cockpit createCockpit() { return new F16Cockpit(); }
  public Wings createWings() { return new F16Wings(); }
}

// Implementations are abstract but limited
class F16AbstractedFactory {
  public void main(F16Factory f16Factory) {
    Engine f16Engine = f16Factory.createEngine(); // abstraction can be changed later on
    Cockpit f16Cockpit = f16Factory.createCockpit(); // abstraction can be changed later on
    Wings f16Wings = f16Factory.createWings(); // abstraction can be changed later on
    List<Engine> engines = new ArrayList<>();
    engines.add(f16Engine);
    for (Engine engine : engines) engine.start();
  }
}

// 1. Abstract Factory
// Abstract and returns abstract implementations
interface AircraftFactory {
  Engine createEngine();
  Cockpit createCockpit();
  Wings createWings();
}

// 2. Concrete Factory
class F16AbstractFactory implements AircraftFactory {
  @Override public Engine createEngine() { return new F16Engine(); }
  @Override  public Cockpit createCockpit() { return new F16Cockpit(); }
  @Override public Wings createWings() { return new F16Wings(); }
}

class Boeing747AbstractFactory implements AircraftFactory {
  @Override public Engine createEngine() { return new F16Engine(); }
  @Override  public Cockpit createCockpit() { return new F16Cockpit(); }
  @Override public Wings createWings() { return new F16Wings(); }
}

// 5. Client (end-user of the factory)
class Aircraft {
  private final AircraftFactory factory;
  private Engine engine;
  private Cockpit cockpit;
  private Wings wings;

  public Aircraft(AircraftFactory factory) {
    this.factory = factory;
  }

  public Aircraft makeAircraft() {
    engine = factory.createEngine();
    cockpit = factory.createCockpit();
    wings = factory.createWings();
    return this;
  }

  public void fly() {
    Aircraft aircraft = makeAircraft();
    aircraft.engine.start();
  }
}
