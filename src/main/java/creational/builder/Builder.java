package creational.builder;

// Builder pattern:
// 1. Creates object in multiple steps (abstract factory creates in one go)
// 2. Hides and decouples object creation from its representation
// 3. Allows to reuse the same construction process
// 4. Antidote for telescoping constructors
// Examples in JDK:
// 1. java.lang.StringBuilder
// 2. java.util.stream.Stream
// 3. java.util.Locale
// Components:
// 1. Builder (build())
// 2. Concrete Builder (build(), getResult())
// 3. Director (construct())
// 4. Product (construct())

// Product
public class Builder {
  public static void main(String[] args) {
    { // Build Boeing747
      AircraftBuilder builder = new Boeing747Builder();
      Director director = new Director(builder);
      director.construct(false);

      Aircraft aircraft = builder.getResult();
    }

    { // Build F16
      AircraftBuilder builder = new F16Builder();
      Director director = new Director(builder);
      director.construct(false);

      Aircraft aircraft = builder.getResult();
    }
  }
}

interface Aircraft {}
class Boeing747 implements Aircraft {}
class F16 implements Aircraft {}

// Builder
abstract class AircraftBuilder {
  public void buildEngine() {}
  public void buildWings() {}
  public void buildCockpit() {}
  public void buildBathrooms() {}
  public abstract Aircraft getResult();
}

// Concrete Builder
class Boeing747Builder extends AircraftBuilder {
  private final Boeing747 aircraft;
  public Boeing747Builder() { aircraft = new Boeing747(); }
  @Override public void buildEngine() {}
  @Override public void buildWings() {}
  @Override public void buildCockpit() {}
  @Override public void buildBathrooms() {}
  @Override public Boeing747 getResult() { return aircraft; }
}

// Concrete Builder
class F16Builder extends AircraftBuilder {
  private final F16 aircraft;
  public F16Builder() { aircraft = new F16(); }
  @Override public void buildEngine() {}
  @Override public void buildWings() {}
  @Override public void buildCockpit() {}
  @Override public void buildBathrooms() {}
  @Override public F16 getResult() { return aircraft; }
}

// Director
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
