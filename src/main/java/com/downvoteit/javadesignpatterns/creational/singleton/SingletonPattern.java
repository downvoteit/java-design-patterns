package com.downvoteit.javadesignpatterns.creational.singleton;

/**
 * Singleton pattern
 *
 * Details
 * 1. Ensures that only one instance of a class exists
 * 2. Ensures that a global point of access exists
 * 3. Can be eagerly or lazily created, synchronized or double-checked locked
 * 4. Enums are singletons by design
 *
 * Components
 * 1. Static field
 * 2. Private/protected constructor
 * 3. Static factory method
 * 4. Product
 *
 * Implementations
 * 1. Lazy creation
 * 2. Synchronized lazy creation
 * 3. Double-checked locked lazy creation (conditionally synchronized)
 * 4. Eager creation
 *
 * Examples in JDK
 * 1. java.lang.Runtime
 * 2. java.awt.Desktop
 * 3. java.nio.file.FileSystems
 *
 * Examples in Spring Framework
 * 1. One Spring Bean per Spring IoC container (application context)
 *
 */

// 4. Product
public class SingletonPattern {
  public static void main(String[] args) {
    { // Lazy creation
      AirForceOneLazy airForceOne = AirForceOneLazy.getInstance();
      airForceOne.fly();
    }
    { // Eager creation
      AirForceOneEager airForceOne = AirForceOneEager.getInstance();
      airForceOne.fly();
    }
    { // Synchronized static factory method
      AirForceOneSynchronized airForceOne = AirForceOneSynchronized.getInstance();
      airForceOne.fly();
    }
    { // Conditionally synchronized static factory method
      AirForceOneDoubleCheckedLocking airForceOne = AirForceOneDoubleCheckedLocking.getInstance();
      airForceOne.fly();
    }
  }
}

// Static field, private/protected constructor, static factory method
class AirForceOneLazy {
  private static AirForceOneLazy instance; // 1. Static field
  private AirForceOneLazy() {} // 2. Private/protected constructor
  public void fly() {}
  public static AirForceOneLazy getInstance() { // 3. Static factory method
    if (instance == null) instance = new AirForceOneLazy();
    return instance;
  }
}

// Static field, private/protected constructor, static factory method
class AirForceOneEager {
  private static final AirForceOneEager instance = new AirForceOneEager();
  private AirForceOneEager() {}
  public void fly() {}
  public static AirForceOneEager getInstance() {
    return instance;
  }
}

// Static field, private/protected constructor, static synchronized factory method
class AirForceOneSynchronized {
  private static AirForceOneSynchronized instance;
  private AirForceOneSynchronized() {}
  public void fly() {}
  public synchronized static AirForceOneSynchronized getInstance() {
    if (instance == null) instance = new AirForceOneSynchronized();
    return instance;
  }
}

// Static field, private/protected constructor, static factory method with conditional synchronization
class AirForceOneDoubleCheckedLocking {
  private volatile static AirForceOneDoubleCheckedLocking instance;
  private AirForceOneDoubleCheckedLocking() {}
  public void fly() {}
  public static AirForceOneDoubleCheckedLocking getInstance() {
    if (instance == null)
      synchronized (AirForceOneDoubleCheckedLocking.class) {
        if (instance == null) instance = new AirForceOneDoubleCheckedLocking();
      }
    return instance;
  }
}
