package structural.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Composite pattern
 *
 * Details
 * 1. Composes objects into a tree structure to represent part-whole hierarchies
 * 2. Lets clients uniformly treat individual objects and composition of objects
 * 3. Allows the client to ignore the differences between the whole and the part
 * 4. Uses iterators and recursion for the traversal of both without the need of any conditionals
 * 5. Both the composite and the leaf components are related by their abstraction
 * 6. Sometimes the components can be distinguished by the unimplemented methods that return UnsupportedOperationException
 *
 * Components
 * 1. Component
 * 2. Leaf
 * 3. Composite
 * 4. Client
 *
 * Implementations
 * 1. Option for custom ordering (e.g. pre-order, in-order, post-order)
 * 2. Caching the already visited nodes for search
 *
 * Examples in JDK
 * 1. javax.faces.component.UIComponent
 *
 */

// 4. Client
public class CompositePattern {
  public static void main(String[] args) {
    AirForce natoAlliance = new AirForce("NATOAirForce"); // root
    AirForce canadianAirForce = new AirForce("CanadianAirForce"); // sub-root
    AirForce usaAirForce = new AirForce("USAAirForce"); // sub-root
    natoAlliance.add(canadianAirForce);
    natoAlliance.add(usaAirForce);
    F16 frenchF16 = new F16(); // leaf
    C130Hercules germanC130Hercules = new C130Hercules(); // leaf
    natoAlliance.add(frenchF16);
    natoAlliance.add(germanC130Hercules);
    System.out.println(natoAlliance.getPersonnel()); // 1 + 5 = 6
  }
}

// 1. Component
interface Aircraft {}
interface AlliancePart { int getPersonnel(); }

// 2. Leaf (parts)
class F16 implements Aircraft, AlliancePart { @Override public int getPersonnel() { return 1; } }
class C130Hercules implements Aircraft, AlliancePart { @Override public int getPersonnel() { return 5; } }

// 3. Composite (whole)
class AirForce implements AlliancePart {
  private final String name;
  private final List<AlliancePart> parts = new ArrayList<>();
  public AirForce(String name) { this.name = name; }
  public void add(AlliancePart part) { parts.add(part); }
  @Override
  public int getPersonnel() { // recursive function
    Iterator<AlliancePart> iterator = parts.iterator(); // internal iterator (client has not access to it)
    int totalPersonnel = 0;
    while (iterator.hasNext()) totalPersonnel += iterator.next().getPersonnel();
    return totalPersonnel;
  }
}
