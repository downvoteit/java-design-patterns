package structural.bridge;

// Bridge pattern

// Details
// 1. Allows to vary abstractions independently of implementations thus allowing to decouple the two in process
// 2. Abstraction here are not necessary meant to be abstract classes and/or interfaces
// 3. Allows switching implementations in runtime (via composition) to avoid permanent binding
// 4. Client can continue to work with the object of the abstraction layer without noticing any changes to the implementation layer classes
// 5. Often confused with the adapter pattern that is applied after the system has been designed
// 6. Is instead applied as part of the design process to decouple two layers

// Components
// 1. Abstraction
// 2. Refined Abstraction
// 3. Implementor
// 4. Concrete Implementor
// 5. Client

// Implementations

// Examples in JDK
// 1. JDBC API interfaces such as javax.sql.DataSource, javax.sql.PooledConnection, javax.sql.rowset
// 2. Collection classes of java.util.List implemented by ArrayList

// 5. Client
public class BridgePattern {
  public static void main(String[] args) {
    AbstractCorolla corolla = new CorollaModelL(new CorollaModelLAsia());
    corolla.listSafetyEquipment();
    // switch implementation in runtime
    corolla.setAbstractCorollaImpl(new CorollaModelLNorthAmerica());
    corolla.listSafetyEquipment();
  }
}

// 1. Abstraction (parallel class of AbstractCorollaImpl)
abstract class AbstractCorolla {
  protected AbstractCorollaImpl abstractCorollaImpl;
  public AbstractCorolla(AbstractCorollaImpl abstractCorollaImpl) {this.abstractCorollaImpl = abstractCorollaImpl; }
  public void setAbstractCorollaImpl(AbstractCorollaImpl abstractCorollaImpl) { this.abstractCorollaImpl = abstractCorollaImpl; }
  abstract void listSafetyEquipment();
  abstract boolean isCardRightHanded();
}

// 2. Refined Abstraction (parallel class to AbstractCorolla)
abstract class AbstractCorollaImpl {
  abstract void listSafetyEquipment();
  abstract boolean isCardRightHanded();
}

// 3. Implementor
class CorollaModelL extends AbstractCorolla {
  public CorollaModelL(AbstractCorollaImpl abstractCorollaImpl) { super(abstractCorollaImpl); }
  @Override void listSafetyEquipment() { abstractCorollaImpl.listSafetyEquipment(); }
  @Override boolean isCardRightHanded() { return abstractCorollaImpl.isCardRightHanded(); }
}

// 4. Concrete Implementor
class CorollaModelLAsia extends AbstractCorollaImpl {
  @Override void listSafetyEquipment() { System.out.println("Low safe standards"); }
  @Override boolean isCardRightHanded() { return false; }
}

class CorollaModelLNorthAmerica extends AbstractCorollaImpl {
  @Override void listSafetyEquipment() { System.out.println("Highly safe standards"); }
  @Override boolean isCardRightHanded() { return true; }
}
