package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test simple pins logic
 */
public class SimplePins {

  public static List<IntListVal> simplePins() {
    return Arrays.asList(new IntListVal(0, 0, 0), new IntListVal(1, 1, 0), new IntListVal(4, 1, 3),
        new IntListVal(12, 1, 3, 5, 2, 1, 0), new IntListVal(6, 1, 0, 5, 0));
  }

  @ParameterizedTest
  @MethodSource("simplePins")
  public void test(IntListVal input) {
    assertEquals(new BowlingGame(input.vals).score(), input.expected);
  }
}
