package behavioral.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Visitor pattern
 *
 * Details
 * 1. Define new operations to be performed on the elements of an object structure without changing the classes of the elements it works on
 * 2. Add new functionality to a composite object without directly modifying the object
 * 3. Suitable when the original object does not change often but still requires new operations to be defined
 * 4. Can be prone to Double Dispatch effect (incorrect object at runtime)
 * 5. Visitor pattern must be used with care as it is sensitive to change
 * 6. Iterator of the Visitor object does not need to use a related parametrized type (i.e. any type of the composite can be used in traversal)
 *
 * Components
 * 1. Visitor
 * 2. Concrete Visitor
 * 3. Element
 * 4. Concrete Element
 * 5. Object Structure
 *
 * Implementations
 *
 * Examples in JDK
 * 1. java.nio.file.FileVisitor interface and java.nio.file.SimpleFileVisitor class
 * 2. javax.lang.model.element.Element and javax.lang.model.element.ElementVisitor interfaces
 *
 */

public class VisitorPattern {
  public static void main(String[] args) {
    AirForce airForce = new AirForce();
    Iterator<Aircraft> planes = airForce.getIterator();
    AircraftMetricsVisitor metricsVisitor = new AircraftMetricsVisitor();
    AircraftPriceVisitor priceVisitor = new AircraftPriceVisitor();
    // Pass the visitors
    while (planes.hasNext()) {
      Aircraft aircraft = planes.next();
      // Double Dispatch (there is only one object in heap no matter the reference type)
      aircraft.accept(metricsVisitor); // < second dispatch
      //   ^ first dispatch
      aircraft.accept(priceVisitor);
    }
    // Output the result of their interaction with the underlying objects
    metricsVisitor.printAccumulatedResults(); // 3 + 4 = 7
    priceVisitor.printAccumulatedResults(); // 15 * 3 + 20 * 2 = 85
  }
}

// 1. Visitor
interface AircraftVisitor {
  void visitF16(F16 f16);
  void visitBoeing747(Boeing747 boeing747);
}

// 2. Concrete Visitor
class AircraftMetricsVisitor implements AircraftVisitor {
  private double metrics;
  @Override public void visitF16(F16 f16) { metrics += 1; }
  @Override public void visitBoeing747(Boeing747 boeing747) { metrics += 2; }
  public void printAccumulatedResults() { System.out.println("Metrics: " + metrics); } // new operation
}

class AircraftPriceVisitor implements AircraftVisitor {
  private double price;
  @Override public void visitF16(F16 f16) { price += 15; }
  @Override public void visitBoeing747(Boeing747 boeing747) { price += 20; }
  public void printAccumulatedResults() {System.out.println("Metrics: " + price); } // new operation
}

// 3. Element
interface Aircraft { void accept(AircraftVisitor visitor); }

// 4. Concrete Element
class F16 implements Aircraft { @Override public void accept(AircraftVisitor visitor) { visitor.visitF16(this); } }
class Boeing747 implements Aircraft { @Override public void accept(AircraftVisitor visitor) { visitor.visitBoeing747(this); } }

class AirForce {
  private final List<Aircraft> planes = new ArrayList<>();
  public AirForce() {
    planes.add(new F16());
    planes.add(new F16());
    planes.add(new F16());
    planes.add(new Boeing747());
    planes.add(new Boeing747());
  }
  public Iterator<Aircraft> getIterator() { return planes.iterator(); }
}
