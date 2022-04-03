package structural.adapter;

/**
 * Adapter pattern
 *
 * Details
 * 1. Allows to bridge incompatible types
 * 2. Comprised of Object (i.e. composition) and Class (i.e. multiple class inheritance) adapter types
 * 3. Object adapter utilizes composition and an abstraction when designing the adapter
 * 4. Class adapter extends both the target type and the adaptee
 * 5. Class adapter can override or add new methods
 *
 * Components
 * 1. Target
 * 2. Client
 * 3. Adaptee
 * 4. Adapter
 *
 * Implementations
 * 1. Object adapter
 * 2. Class adapter
 *
 * Examples in JDK
 * 1. XML, JSON, Text parsers
 * 2. java.util.Enumeration to java.util.Iterator adapter
 * 3. java.io.InputStreamReader
 * 4. java.io.OutputStreamWriter
 *
 */

// 2. Client
public class AdapterPattern {
  public static void main(String[] args) {
    // Object adapter
    // HotAirBalloon class is now compatible with Aircraft interface
    HotAirBalloon hotAirBalloon = new HotAirBalloon();
    Adapter hotAirBalloonAdapter = new Adapter(hotAirBalloon);
    hotAirBalloonAdapter.fly();
  }
}

// 1. Target
interface Aircraft { void fly(); }

// 3. Adaptee
class HotAirBalloon {
  private String gasUsed = "Helium";
  public void fly(String gasUsed) {} // is incompatible with Aircraft interface
  public String inflateWithUsed() { return gasUsed; }
}

// 4. Adapter (object adapter)
class Adapter implements Aircraft {
  private HotAirBalloon hotAirBalloon;
  public Adapter(HotAirBalloon hotAirBalloon) { this.hotAirBalloon = hotAirBalloon; }
  @Override public void fly() {
    String fuelUsed = hotAirBalloon.inflateWithUsed();
    hotAirBalloon.fly(fuelUsed);
  }
}
