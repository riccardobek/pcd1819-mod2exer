package pcd2018.lab1.bowling;

import io.vavr.collection.List;

/**
 * Support class for parametric tests.
 */
class IntListVal {

  public final List<Integer> vals;
  public final int expected;

  IntListVal(int expected, Integer... vals) {
    this.expected = expected;
    this.vals = List.of(vals);
  }

}
