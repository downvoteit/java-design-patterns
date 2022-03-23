package creational.singleton;

// Singleton pattern:
// 1. Ensures that only one instance of a class exists
// 2. Ensures that a global point of access exists
// 3. Can be eagerly or lazily created, synchronized or double-checked locked
// 4. Enums are singletons by design
// Examples in JDK:
// 1. java.lang.Runtime
// 2. java.awt.Desktop
// Components:
// 1. Static field
// 2. Private/protected constructor
// 3. Static factory method
// 4. Product

// Product
public class Singleton {
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
    { // Synchronized static factory method
      AirForceOneDoubleCheckedLocking airForceOne = AirForceOneDoubleCheckedLocking.getInstance();
      airForceOne.fly();
    }
  }
}

// Static field, private/protected constructor, static factory method
class AirForceOneLazy {
  private static AirForceOneLazy instance;
  private AirForceOneLazy() {}
  public void fly() {}

  public static AirForceOneLazy getInstance() {
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

  public synchronized static  AirForceOneSynchronized getInstance() {
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
