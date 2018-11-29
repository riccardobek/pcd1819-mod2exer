package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test Strike scoring logic
 */
public class ShortStrikes {

  public static List<IntListVal> shortStikes() {
    return Arrays.asList(new IntListVal(10, 10, 0, 0), new IntListVal(16, 10, 0, 0, 5, 1),
        new IntListVal(22, 10, 5, 1));
  }

  @ParameterizedTest
  @MethodSource("shortStikes")
  public void test(IntListVal input) {
    assertEquals(new BowlingGame(input.vals).score(), input.expected);
  }
}
