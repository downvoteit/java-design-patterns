package structural.flyweight;

// Flyweight pattern

// Details
// 1. Allows the sharing of state among a large number of fine-grained object for efficiency
// 2. Facilitates the separation of states into intrinsic (shared/immutable context) and extrinsic (mutable context) sections
// 3. Reduces the number of objects and the memory usage of the program through partitioning and updating only the mutable part
// 4. In other words breaks up a single heavyweight object into two (or more) flyweight objects

// Components
// 1. Flyweight
// 2. Concrete Flyweight
// (3. Unshared Concrete Flyweight)
// (4. Flyweight Factory)
// 5. Client

// Implementations

// Examples in JDK
// 1. java.lang.Boolean.valueOf
// 2. java.lang.Integer.valueOf

import java.util.HashMap;
import java.util.Map;

// 5. Client
public class FlyweightPattern {
  public static void main(String[] args) {
    F16Factory f16Factory = new F16Factory()
        .getF16("A")
        .getF16("B-unshared")
        .getF16("C-unshared")
        .getF16("D");
    Map<String, Aircraft> pool = f16Factory.getPool();
    for (String name: pool.keySet()) {
      Aircraft aircraft = pool.get(name);
      for (int[] coordinate : new int[][]{{2, 4}}) {
        // passing an extrinsic state to the flyweight object
        int currX = coordinate[0];
        int currY = coordinate[1];
        System.out.println(aircraft + " destination time: " + aircraft.getTimeToDest(currX, currY, 10, 10, 200));
      }
    }
  }
}

class F16Heavyweight {

}

// 1. Flyweight
interface Aircraft {
  double getTimeToDest(int currX, int currY, int destX, int destY, int currSpeed);
}

class Data { // common data shared among many objects
  private final int personnel = 2;
  private final String dimensions = "15m long 3m wide";
  private final String wingspan = "33 feet";
}

// 2. Concrete Flyweight
class F16 implements Aircraft {
  private final String name;
  private final Data data; // intrinsic state
  public F16(String name, Data data) {
    this.name = name;
    this.data = data;
  }
  @Override
  public double getTimeToDest(int currX, int currY, int destX, int destY, int currSpeed) { // extrinsic state
    return Math.round(Math.random() * 10) + 1;
  }
  @Override
  public String toString() {
    return "" + name;
  }
}

// 3. Unshared Concrete Flyweight
class F16Unshared implements Aircraft {
  private final String name;
  public F16Unshared(String name) { this.name = name; }
  @Override
  public double getTimeToDest(int currX, int currY, int destX, int destY, int currSpeed) { // extrinsic state
    return Math.round(Math.random() * 10) + 1;
  }
  @Override
  public String toString() {
    return "" + name.replace("-unshared", "");
  }
}

// 4. Flyweight Factor
class F16Factory {
  private final Map<String, Aircraft> pool = new HashMap<>();
  public Map<String, Aircraft> getPool() { return new HashMap<>(pool); }
  protected F16Factory getF16(String name) { // factory method
    if (!pool.containsKey(name)) pool.put(name, name.contains("unshared") ? new F16Unshared(name) : new F16(name, new Data()));
    return this;
  }
}
