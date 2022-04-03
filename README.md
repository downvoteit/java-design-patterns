# java-design-patterns

## Purpose

- Learn design patterns and where they are applied in JDK

## Learning resources

- Educative Software Design Patterns: Best Practices for Software Developers
  - https://www.educative.io/courses/software-design-patterns-best-practices
- Head First Design Patterns 2nd Ed
  - By Freeman E. et al.

## Categories

- Creational patterns (control class creation and encapsulation)
  - Builder
    - [Boeing 747 and F16 build example](src/main/java/creational/builder/BuilderPattern.java)
  - Singleton
    - [Air Force One example](src/main/java/creational/singleton/SingletonPattern.java)
  - Prototype
    - [F16 engine substitution example](src/main/java/creational/prototype/PrototypePattern.java)
  - Factory Method
    - [F16 engine substitution example](src/main/java/creational/factorymethod/FactoryMethodPattern.java)
  - Abstract Factory
    - [F16 engine substitution and simulation example](src/main/java/creational/abstractfactory/AbstractFactoryPattern.java)
- Structural patterns (control class composition)
  - Adapter
    - [Aircraft software (Aircraft to HotAirBallon adapter) example](src/main/java/structural/adapter/AdapterPattern.java)
  - Bridge
  - Composite
  - Decorator
  - Facade
    - [Aircraft autopilot example](src/main/java/structural/facade/FacadePattern.java)
  - Flyweight
  - Proxy
- Behavioral patterns (control class interaction and responsibility)
  - Chain of Responsibility
  - Observer
    - [Air-traffic controller example (push model)](src/main/java/behavioral/observer/push/ObserverPattern.java)
    - [Air-traffic controller example (pull model)](src/main/java/behavioral/observer/pull/ObserverPattern.java)
  - Interpreter
  - Command
  - Iterator
  - Mediator
  - Memento
  - State
  - Template
  - Strategy
  - Visitor
