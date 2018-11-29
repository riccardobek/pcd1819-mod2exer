package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test Spare scoring logic
 */
public class ShortSpares {

  public static List<IntListVal> shortSpares() {
    return Arrays.asList(new IntListVal(10, 1, 9, 0, 0), new IntListVal(15, 1, 9, 0, 5),
        new IntListVal(21, 1, 9, 3, 5, 0, 0), new IntListVal(30, 1, 9, 3, 7, 2, 3));
  }

  @ParameterizedTest
  @MethodSource("shortSpares")
  public void test(IntListVal input) {
    assertEquals(new BowlingGame(input.vals).score(), input.expected);
  }
}
