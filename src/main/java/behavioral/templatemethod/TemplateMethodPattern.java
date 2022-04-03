package behavioral.templatemethod;

/**
 * Template Method pattern
 *
 * Details
 * 1. Allows subclasses to define parts of an algorithm without modifying the overall structure of the algorithm
 * 2. Is useful when the order of execution must be maintained but the class is to be open for modification (Open/closed principle)
 * 3. Comprised of abstract methods that are invoked in a fixed sequence (if not overriden the default implementation is used)
 * 4. Can forbid method override by marking it as final
 * 5. Methods marked with abstract will for the client to override and provide the necessary implementation
 * 6. Method hooks can be used to the for optional steps
 * 7. Facilitates code reuse, loose coupling between high and low level components (i.e. prevents Dependency Rot)
 * 8. Lower level components (subclasses) will depend on higher level abstractions
 * 9. Must not confuse it with Strategy pattern that uses composition instead of inheritance
 * 10. Factory method pattern is a specialization of the template method pattern
 * 11. Number of mandatory methods for overriding must be limited (Interface Segregation principle)
 *
 * Components
 * 1. Abstract Class
 * 2. Concrete Class
 * 3. Client
 *
 * Implementations
 *
 * Examples in JDK
 * 1. Java Applets
 * 2. Java IO InputStream read() and OutputStream write() methods
 * 3. javax.servlet.http.HttpServlet doGet(), doPost() and doPut() methods
 *
 * Examples in Spring Framework
 * 1. Spring MVC
 * 2. Spring Transactions
 * 3. Spring Security
 * 4. Spring JDBC Template
 *
 */

// 3. Client
public class TemplateMethodPattern {
  public static void main(String[] args) {
    F16PreFlightCheckList flightCheckList = new F16PreFlightCheckList();
    flightCheckList.runCheckList();
  }
}

// 1. Abstract Class
abstract class AbstractPreFlightCheckList {
  public final void runCheckList() {
    IsFuelEnough();
    doorsLocked();
    checkAirPressure();
  }
  public final void IsFuelEnough() {}
  protected void doorsLocked() {}
  public abstract void checkAirPressure();
}

// 2. Concrete Class
class F16PreFlightCheckList extends AbstractPreFlightCheckList {
  @Override protected void doorsLocked() {} // optional override (hook)
  @Override public void checkAirPressure() {} // mandatory override
}
