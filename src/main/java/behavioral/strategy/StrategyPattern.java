package behavioral.strategy;

/**
 * Strategy pattern
 *
 * Details
 * 1. Allows to group/encapsulate related algorithms under an abstraction that can be exposed to the client
 * 2. The abstraction can then allow to switch out an algorithm in runtime
 * 3. Uses composition as opposed to inheritance employed by the Template Method pattern
 * 4. Strategy objects are good candidates for Flyweight objects (i.e. their memory usage can be further optimized)
 * 5. Context object can contain a default strategy when none are provided by the client
 *
 * Components
 * 1. Strategy
 * 2. Concrete Strategy
 * 3. Context
 * 4. Client
 *
 * Implementations
 * 1. Flyweight optimization
 *
 * Examples in JDK
 * 1. java.util.Comparator
 *
 */

// 4. Client
public class StrategyPattern {
  public static void main(String[] args) {
      BubbleSort bubbleSort = new BubbleSort();
      MergeSort mergeSort = new MergeSort();
      Context context = new Context(bubbleSort);
      int[] numsOne = {3, 4, 2, 1, 5};
      int[] numsTwo = {3, 4, 2, 1, 5};
      context.sort(numsOne);
      context.setSortAlgorithm(mergeSort);
      context.sort(numsTwo);
  }
}

// 1. Strategy
interface Sort { void sort(int[] nums); }

// 2. Concrete Strategy
class BubbleSort implements Sort { @Override public void sort(int[] nums) {} }
class MergeSort implements Sort { @Override public void sort(int[] nums) {} }

// 3. Context
class Context {
  private Sort sortAlgorithm;
  public Context(Sort sortAlgorithm) { this.sortAlgorithm = sortAlgorithm; }
  public void sort(int[] nums) { sortAlgorithm.sort(nums); }
  public void setSortAlgorithm(Sort sortAlgorithm) { this.sortAlgorithm = sortAlgorithm; } // change algorithm in runtime
}
