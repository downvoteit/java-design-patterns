package com.downvoteit.javadesignpatterns.creational.prototype;

/**
 * Prototype pattern
 *
 * Details
 * 1. Creates a new object by copying an existing
 * 2. Less expensive than creating a new object
 * 3. When a class constructor is inaccessible can request a clone from the manager
 * 4. Reduces the number of classes by cloning with different values from the model
 * 5. Cloning can be shallow (some value sharing) or deep (no value sharing)
 * 6. Prone to circular dependency
 *
 * Components
 * 1. Prototype (clone())
 * 2. Concrete Prototype (clone())
 * 3. Variables (subclasses/subtypes)
 * 4. Client (set())
 *
 * Implementations
 * 1. Shallow copy clone method
 * 2. Deep copy clone method
 *
 * Examples in JDK
 * 1. java.lang.Cloneable
 */

// 4. Client
public class PrototypePattern {
  public static void main(String[] args) {
    // Base variant
    AircraftPrototype aircraftPrototype = new F16();
    // A variant
    AircraftPrototype f16A = aircraftPrototype.clone();
    f16A.setEngine(new F16AEngine());
    // B variant
    AircraftPrototype f16B = aircraftPrototype.clone();
    f16B.setEngine(new F16BEngine());
  }
}

// 3. Variables
class F16Engine {}
class F16AEngine extends F16Engine {}
class F16BEngine extends F16Engine {}

// 1. Prototype
interface AircraftPrototype {
  void fly();
  AircraftPrototype clone();
  void setEngine(F16Engine f16Engine);
}

// 2. Concrete Prototype
class F16 implements AircraftPrototype {
  private F16Engine f16Engine = new F16Engine();
  @Override public void fly() {}
  @Override public AircraftPrototype clone() { return new F16(); }
  @Override public void setEngine(F16Engine f16Engine) { this.f16Engine = f16Engine; }
}
