package pcd2018.lab2.bowling;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Support class to write the tests.
 *
 */
class IntListResult {
  final int result;
  final String status;
  final ArrayList<Integer> pins;

  IntListResult(int result, String status, int... pins) {
    this.result = result;
    this.status = status;
    this.pins = new ArrayList<Integer>(pins.length);
    for (int pin : pins)
      this.pins.add(pin);
  }

  @Override
  public String toString() {
    return "[" + result + ":" + status + " |" + pins.stream().map(i -> i.toString()).collect(Collectors.joining(" "))
        + "]";
  }
}
