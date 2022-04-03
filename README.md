# java-design-patterns

## Purpose

- Learn design patterns and where they are applied in JDK and Spring Framework

## Learning resources

- Educative Software Design Patterns: Best Practices for Software Developers
  - https://www.educative.io/courses/software-design-patterns-best-practices
- Baeldung articles on Spring Framework Design Patterns
  - https://www.baeldung.com/spring-framework-design-patterns
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
    - [Aircraft to HotAirBalloon adapter example](src/main/java/structural/adapter/AdapterPattern.java)
  - Bridge
    - [Toyota car sales software example](src/main/java/structural/bridge/BridgePattern.java)
  - Composite
    - [NATO Air force example](src/main/java/structural/composite/CompositePattern.java)
  - Decorator
    - [Boeing 747 fitting example](src/main/java/structural/decorator/DecoratorPattern.java)
  - Facade
    - [Aircraft autopilot example](src/main/java/structural/facade/FacadePattern.java)
  - Flyweight
    - [Aircraft ground radar example](src/main/java/structural/flyweight/FlyweightPattern.java)
  - Proxy
    - [Drone controls example (remote proxy)](src/main/java/structural/proxy/ProxyPattern.java)
- Behavioral patterns (control class interaction and responsibility)
  - Chain of Responsibility
  - Observer
    - [Air-traffic controller example (push model)](src/main/java/behavioral/observer/push/ObserverPattern.java)
    - [Air-traffic controller example (pull model)](src/main/java/behavioral/observer/pull/ObserverPattern.java)
  - Interpreter
  - Command
  - Iterator
    - [Aircraft and custom Java Iterator example](src/main/java/behavioral/iterator/IteratorPattern.java)
  - Mediator
  - Memento
  - State
  - Template
    - [Aircraft pre-flight checklist example](src/main/java/behavioral/templatemethod/TemplateMethodPattern.java)
  - Strategy
    - [Sorting algorithms example](src/main/java/behavioral/strategy/StrategyPattern.java)
  - Visitor

## Additional information

- Java files linted for most compaction
