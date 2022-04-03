package structural.decorator;

/**
 * Decorator pattern
 *
 * Details
 * 1. Dynamically enhance or extend behaviour of an object by wrapping it into another related object
 * 2. Is an alternative to subclassing
 * 3. Adds new behaviour either before or after the invocation of wrapper methods
 * 4. Can lead to many decorator classes
 * 5. Tight coupling/reference with a particular decorator class can be lost after wrapping
 *
 * Components
 * 1. Component
 * 2. Concrete Component
 * 3. Decorator
 * 4. Concrete Decorator
 * 5. Client
 *
 * Implementations
 *
 * Examples in JDK
 * 1. Java IO classes such as InputStream, FileInputStream, BufferedInputStream etc
 */

// 5. Client
public class DecoratorPattern {
  public static void main(String[] args) {
    // weight after wrapping 100 + 30 + 50 = 180
    Aircraft simpleBoeing = new Boeing747();
    Aircraft luxuryBoeing = new LuxuryFittings(simpleBoeing);
    Aircraft bulletproofBoeing = new BulletproofFittings(luxuryBoeing);
    System.out.println(bulletproofBoeing.getBaseWeight());
  }
}

// 1. Component
interface Aircraft {
  float baseWeight = 100;
  float getBaseWeight();
  void fly();
  void land();
}

// 2. Concrete Component
class Boeing747 implements Aircraft {
  @Override public float getBaseWeight() { return baseWeight; }
  @Override public void fly() {}
  @Override public void land() {}
}

// 3. Decorator
abstract class BoeingDecorator implements Aircraft {}

// 4. Concrete Decorator
class LuxuryFittings extends BoeingDecorator {
  private final Aircraft aircraft; // composition in use
  public LuxuryFittings(Aircraft aircraft) { this.aircraft = aircraft; }
  @Override public float getBaseWeight() { return 30f + aircraft.getBaseWeight(); } // 30 + 100
  @Override public void fly() { aircraft.fly(); }
  @Override public void land() { aircraft.land(); }
}

class BulletproofFittings extends BoeingDecorator {
  private final Aircraft aircraft; // composition in use
  public BulletproofFittings(Aircraft aircraft) { this.aircraft = aircraft; }
  @Override public float getBaseWeight() { return 50f + aircraft.getBaseWeight(); } // 50 + 100
  @Override public void fly() { aircraft.fly(); }
  @Override public void land() { aircraft.land(); }
}
