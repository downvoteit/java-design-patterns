package com.downvoteit.javadesignpatterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator pattern
 *
 * Details
 * 1. Allows the consumer to traverse the elements of an aggregate/collection without knowing how the inner data structure is implemented
 * 2. Extracts the responsibility of traversing and hides the internal implementation
 * 3. Can use Memento pattern to store traverse state
 *
 * Components
 * 1. Iterator
 * 2. Concrete Iterator
 * 3. Aggregate
 * 4. Concrete Aggregate
 * 5. Client
 *
 * Implementations
 * 1. External iterator (client asks for next element)
 * 2. Internal iterator (client cannot access it directly; iterator traverses the internal aggregate/collection)
 *
 * Examples in JDK
 * 1. java.util.Iterator
 * 2. java.util.Scanner
 * 3. java.util.Enumeration
 *
 */

// 5. Client
public class IteratorPattern {
  public static void main(String[] args) {
    { // General iterator for all aircraft objects
      AirForce airForce = new AirForce();
      Iterator allAircraft = airForce.createAircraftIterator();
      while (allAircraft.hasNext()) System.out.print(allAircraft.next() + " ");
    }
    System.out.println();
    { // Specific iterator only for jets
      AirForce airForce = new AirForce();
      Iterator jets = airForce.createJetsIterator();
      while (jets.hasNext()) System.out.print(jets.next()  + " ");
    }
    System.out.println();
  }
}

interface Aircraft {}
interface Helicopter extends Aircraft {}

class F16 implements Aircraft { @Override public String toString() { return "F16"; } }
class AHCobra implements Helicopter { @Override public String toString() { return "AHCobra"; } }

// 1. Iterator (External iterator is called by the client)
interface Iterator {
  Aircraft next(); // Internal iterator (works with the underlying aggregate/collection)
  boolean hasNext();
}

class AirForce {
  private final List<Aircraft> jets = new ArrayList<>(3); // 3. Aggregate
  private final Helicopter[] choppers = new Helicopter[2];
  public AirForce() {
    jets.add(new F16());
    jets.add(new F16());
    jets.add(new F16());
    choppers[0] = new AHCobra();
    choppers[1] = new AHCobra();
  }
  public List<Aircraft> getJets() { return jets; }
  public Helicopter[] getChoppers() { return choppers; }
  public Iterator createAircraftIterator() { return new AirForceIterator(this); } // 4. Concrete Aggregate
  public Iterator createJetsIterator() { return new JetsIterator(jets); }
}

// 2. Concrete Iterator
class AirForceIterator implements Iterator {
  private final List<Aircraft> jets;
  private final Helicopter[] choppers;
  private int jetsIndex;
  private int choppersIndex;
  public AirForceIterator(AirForce airForce) {
    this.jets = airForce.getJets();
    this.choppers = airForce.getChoppers();
  }
  @Override
  public Aircraft next() {
    if (jets.size() > jetsIndex) return jets.get(jetsIndex++);
    if (choppers.length > choppersIndex) return choppers[choppersIndex++];
    throw new NoSuchElementException("No more elements");
  }
  @Override public boolean hasNext() { return (jets.size() > jetsIndex) || (choppers.length > choppersIndex); }
}

class JetsIterator implements Iterator {
  private final List<Aircraft> jets;
  private int jetsIndex;
  public JetsIterator(List<Aircraft> jets) { this.jets = jets; }
  @Override
  public Aircraft next() {
    if (jets.size() > jetsIndex) return jets.get(jetsIndex++);
    throw new NoSuchElementException("No more elements");
  }
  @Override public boolean hasNext() { return jets.size() > jetsIndex; }
}
