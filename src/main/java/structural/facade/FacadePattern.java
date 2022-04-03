package structural.facade;

/**
 * Facade pattern
 *
 * Details
 * 1. Simplifies the creation of multiple subsystems
 * 2. Makes the management of multiple subsystems easier
 * 3. Can enforce calling different subsystems in the right order
 * 4. Shields the client from having to deal with complex classes
 * 5. Facilitates loose coupling between subsystems and the client
 * 6. Can provide translation between the various subsystems and the client
 *
 * Components
 * 1. Facade
 * 2. Subsystem classes
 * 3. Client
 *
 * Implementations
 * 1. Singleton
 * 2. Factor method for the subsystems
 *
 * Examples in JDK
 * 1. javax.faces.context.FacesContext
 * 2. javax.faces.context.ExternalContext
 *
 */

// 3. Client
public class FacadePattern {
  public static void main(String[] args) {
    // Subsystems
    AltitudeMonitor altitudeMonitor = new AltitudeMonitor();
    EngineController engineController = new EngineController();
    FuelMonitor fuelMonitor = new FuelMonitor();
    NavigationSystem navigationSystem = new NavigationSystem();
    // Facade that manages the right order of execution
    AutopilotFacade autopilotFacade = new AutopilotFacade(altitudeMonitor, engineController, fuelMonitor, navigationSystem);
    autopilotFacade.autopilotOn();
    autopilotFacade.autopilotOff();
  }
}

// 2. Subsystem classes
class AltitudeMonitor {
  public void autoMonitor() {}
  public void disable() {}
}

class EngineController {
  public void setEngineSpeed(long speed) {}
  public void disable() {}
}

class FuelMonitor {}

class NavigationSystem {
  public void setDirectionBasedOnSpeedAndFuel(AltitudeMonitor altitudeMonitor, FuelMonitor fuelMonitor, EngineController engineController) {}
  public void disable() {}
}

// 1. Facade
class AutopilotFacade {
  private final AltitudeMonitor altitudeMonitor;
  private final EngineController engineController;
  private final FuelMonitor fuelMonitor;
  private final NavigationSystem navigationSystem;

  public AutopilotFacade(AltitudeMonitor altitudeMonitor, EngineController engineController, FuelMonitor fuelMonitor, NavigationSystem navigationSystem) {
    this.altitudeMonitor = altitudeMonitor;
    this.engineController = engineController;
    this.fuelMonitor = fuelMonitor;
    this.navigationSystem = navigationSystem;
  }

  public void autopilotOn() {
    altitudeMonitor.autoMonitor();
    engineController.setEngineSpeed(700);
    navigationSystem.setDirectionBasedOnSpeedAndFuel(altitudeMonitor, fuelMonitor, engineController);
  }

  public void autopilotOff() {
    altitudeMonitor.disable();
    engineController.disable();
    navigationSystem.disable();
  }
}
