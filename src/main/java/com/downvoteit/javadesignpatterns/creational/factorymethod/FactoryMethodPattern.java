package com.downvoteit.javadesignpatterns.creational.factorymethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory Method pattern
 *
 * Details
 * 1. Provides interface for object creation but delegates actual instantiation to subclasses
 * 2. Facilitates coding by abstraction and loose coupling
 * 3. Allows dynamic binding in runtime to meet certain conditions (e.g. OS, Environment variable etc)
 * 4. Superclass holds the general setup, implementing all methods or all except the creation methods (can be abstract)
 * 5. Creation of the right object is the responsibility of the subclasses
 * 6. Can produce varying products through inheritance as opposed to the simple factory
 * 7. Prone to producing too many subclasses with minor differences
 * 8. Superclass downcast can cause ClassCastException in runtime
 *
 * Components
 * 1. Creator (factoryMethod())
 * 2. Concrete Creator (factoryMethod())
 * 3. Product
 * 4. Concrete Product
 *
 * Implementations
 * 1. Extend the superclass and update fields
 *
 * Examples in JDK
 * 1. java.util.Calendar.getInstance()
 * 2. java.util.ResourceBundle.getBundle()
 * 3. java.text.NumberFormat.getInstance()
 *
 * Examples in Spring Framework
 * 1. Spring Bean injection into Spring IoC container (application context)
 *
 */

public class FactoryMethodPattern {
  public static void main(String[] args) {
    { // Naive creation
      F16Naive f16 = new F16Naive();
      f16.fly();
    }
    { // Simple Factory
      F16Concrete f16 = F16SimpleFactory.makeF16("B");
      f16.fly();
    }
    { // Factory Method
      List<F16Abstracted> airForce = new ArrayList<>();
      F16AVariantAbstracted f16A = new F16AVariantAbstracted();
      F16BVariantAbstracted f16B = new F16BVariantAbstracted();
      airForce.add(f16A);
      airForce.add(f16B);
      for (F16Abstracted f16: airForce) f16.fly();
    }
  }
}

// 3. Product
interface F16Engine {}
interface F16Cockpit {}
interface F16Wings {}

// 4. Concrete Product
class F16EngineConcrete implements F16Engine {}
class F16EngineAConcrete implements F16Engine {}
class F16EngineBConcrete implements F16Engine {}
class F16CockpitConcrete implements F16Cockpit {}
class F16WingsConcrete implements F16Wings {}

// Tight coupling
class F16Naive {
  private F16EngineConcrete engine; // concrete implementation
  private F16CockpitConcrete cockpit; // concrete implementation
  private F16WingsConcrete wings; // concrete implementation
  protected void makeF16() {
    this.engine = new F16EngineConcrete();
    this.cockpit = new F16CockpitConcrete();
    this.wings = new F16WingsConcrete();
  }
  public void fly() { makeF16(); }
}

class F16Concrete { public void fly() {} }
class F16AVariantConcrete extends F16Concrete {}
class F16BVariantConcrete extends F16Concrete {}
class F16CVariantConcrete extends F16Concrete {}

// Not extendable (due to method hiding)
class F16SimpleFactory {
  public static F16Concrete makeF16(String variant) {
    switch (variant) { // concrete implementations
      case "A": return new F16AVariantConcrete();
      case "B": return new F16BVariantConcrete();
      case "C": return new F16CVariantConcrete();
      default: return new F16Concrete();
    }
  }
}

// 1. Creator
class F16Abstracted {
  protected F16Engine engine; // abstraction
  protected F16Cockpit cockpit; // abstraction
  protected F16Wings wings; // abstraction
  protected F16Abstracted makeF16() { // factory method
    engine = new F16EngineConcrete();
    cockpit = new F16CockpitConcrete();
    wings = new F16WingsConcrete();
    return this;
  }
  public void fly() { F16Abstracted f16 = makeF16(); }
}

// 2. Concrete Creator
class F16AVariantAbstracted extends F16Abstracted {
  @Override
  public F16Abstracted makeF16() { // factory method
    super.makeF16();
    engine = new F16EngineAConcrete();
    return this;
  }
}

// 2. Concrete Creator
class F16BVariantAbstracted extends F16Abstracted {
  @Override
  public F16Abstracted makeF16() { // factory method
    super.makeF16();
    engine = new F16EngineBConcrete();
    return this;
  }
}
