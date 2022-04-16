package com.downvoteit.javadesignpatterns.creational.builder;

/**
 * Builder pattern
 *
 * Details
 * 1. Creates a new object in multiple steps (abstract factory creates in one go)
 * 2. Hides and decouples object creation from its representation
 * 3. Allows to reuse the same construction process
 * 4. Antidote for telescoping constructors
 *
 * Components
 * 1. Builder (build())
 * 2. Concrete Builder (build(), getResult())
 * 3. Director (construct())
 * 4. Product (construct())
 *
 * Implementations
 * 1. With a director
 * 2. Without a director
 *
 * Examples in JDK
 * 1. java.lang.StringBuilder
 * 2. java.util.stream.Stream
 * 3. java.util.Locale
 *
 */

// 4. Product
public class BuilderPattern {
  public static void main(String[] args) {
    { // Build Boeing747
      AircraftBuilder aircraftBuilder = new Boeing747Builder();
      Director director = new Director(aircraftBuilder);
      director.construct(false);

      Aircraft aircraft = aircraftBuilder.getResult();
      aircraft.fly();
    }
    { // Build F16
      AircraftBuilder aircraftBuilder = new F16Builder();
      Director director = new Director(aircraftBuilder);
      director.construct(false);

      Aircraft aircraft = aircraftBuilder.getResult();
      aircraft.fly();
    }
  }
}

interface Aircraft { default void fly() {} }
class Boeing747 implements Aircraft {}
class F16 implements Aircraft {}

// 1. Builder
abstract class AircraftBuilder {
  public void buildEngine() {}
  public void buildWings() {}
  public void buildCockpit() {}
  public void buildBathrooms() {}
  public abstract Aircraft getResult();
}

// 2. Concrete Builder
class Boeing747Builder extends AircraftBuilder {
  private final Boeing747 aircraft;
  public Boeing747Builder() { aircraft = new Boeing747(); }
  @Override public void buildEngine() {}
  @Override public void buildWings() {}
  @Override public void buildCockpit() {}
  @Override public void buildBathrooms() {}
  @Override public Boeing747 getResult() { return aircraft; }
}

// 2. Concrete Builder
class F16Builder extends AircraftBuilder {
  private final F16 aircraft;
  public F16Builder() { aircraft = new F16(); }
  @Override public void buildEngine() {}
  @Override public void buildWings() {}
  @Override public void buildCockpit() {}
  @Override public void buildBathrooms() {}
  @Override public F16 getResult() { return aircraft; }
}

// 3. Director
class Director {
  private final AircraftBuilder aircraftBuilder;
  public Director(AircraftBuilder aircraftBuilder) { this.aircraftBuilder = aircraftBuilder; }
  public void construct(boolean isPassenger) {
    aircraftBuilder.buildEngine();
    aircraftBuilder.buildCockpit();
    aircraftBuilder.buildWings();
    if (isPassenger) aircraftBuilder.buildBathrooms();
  }
}
