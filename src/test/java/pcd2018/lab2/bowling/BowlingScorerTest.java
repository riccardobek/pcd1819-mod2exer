package pcd2018.lab2.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Adapted version of the bowling kata tests.
 */
public class BowlingScorerTest {

  public static List<IntListResult> data() {
    return Arrays.asList(
        // simple pins
        new IntListResult(0, "--", 0, 0), new IntListResult(1, "1-", 1, 0), new IntListResult(4, "13", 1, 3),
        new IntListResult(12, "13521-", 1, 3, 5, 2, 1, 0), new IntListResult(6, "1-5-", 1, 0, 5, 0),
        // short spares
        new IntListResult(10, "1/--", 1, 9, 0, 0), new IntListResult(15, "1/-5", 1, 9, 0, 5),
        new IntListResult(21, "1/35--", 1, 9, 3, 5, 0, 0), new IntListResult(30, "1/3/23", 1, 9, 3, 7, 2, 3),
        // short strikes
        new IntListResult(10, "X--", 10, 0, 0), new IntListResult(16, "X--51", 10, 0, 0, 5, 1),
        new IntListResult(22, "X51", 10, 5, 1),
        // normal plays
        new IntListResult(103, "1/35XXX45", 1, 9, 3, 5, 10, 10, 10, 4, 5),
        new IntListResult(30, "11111111112222222222", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
        new IntListResult(0, "--------------------", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        new IntListResult(3, "1-1----------------1", 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
        new IntListResult(90, "9-9-9-9-9-9-9-9-9-9-", 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0),
        new IntListResult(26, "5/11------------3/11", 5, 5, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 7, 1, 1),
        new IntListResult(82, "9-8/--9-9-9-9-9-9-9-", 9, 0, 8, 2, 0, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0),
        new IntListResult(12, "--8/1---------------", 0, 0, 8, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        new IntListResult(11, "--8/-1--------------", 0, 0, 8, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        new IntListResult(98, "9-X8-9-9-9-9-9-9-9-", 9, 0, 10, 8, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0),
        new IntListResult(28, "--X81--------------", 0, 0, 10, 8, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        new IntListResult(27, "--X8-1-------------", 0, 0, 10, 8, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        new IntListResult(190, "8/9/72XXX3/819/XX5", 8, 2, 9, 1, 7, 2, 10, 10, 10, 3, 7, 8, 1, 9, 1, 10, 10, 5),
        new IntListResult(126, "X3/4/6-X-1-/-/X54", 10, 3, 7, 4, 6, 6, 0, 10, 0, 1, 0, 10, 0, 10, 10, 5, 4),
        // final frames
        new IntListResult(160, "1/35XXX458/X3/23", 1, 9, 3, 5, 10, 10, 10, 4, 5, 8, 2, 10, 3, 7, 2, 3),
        new IntListResult(189, "1/35XXX458/X3/XX6", 1, 9, 3, 5, 10, 10, 10, 4, 5, 8, 2, 10, 3, 7, 10, 10, 6),
        new IntListResult(149, "1/35XXX458/X35", 1, 9, 3, 5, 10, 10, 10, 4, 5, 8, 2, 10, 3, 5),
        new IntListResult(300, "XXXXXXXXXXXX", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
        new IntListResult(274, "XXXXXXXXXX12", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2),
        new IntListResult(153, "1/35XXX458/X3/--", 1, 9, 3, 5, 10, 10, 10, 4, 5, 8, 2, 10, 3, 7, 0, 0),
        new IntListResult(150, "5/5/5/5/5/5/5/5/5/5/5", 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
        new IntListResult(84, "9-8/--9-9-9-9-9-9-9/1", 9, 0, 8, 2, 0, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 1, 1),
        new IntListResult(104, "9-X8-9-9-9-9-9-9-X23", 9, 0, 10, 8, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 10, 2, 3));
  }

  @ParameterizedTest
  @MethodSource("data")
  @Tag("Bowling")
  public void test(IntListResult input) {
    BowlingScorer bowlingScorer = new BowlingScorer();
    for (int pin : input.pins)
      bowlingScorer = bowlingScorer.pin(pin);
    assertEquals(input.result, bowlingScorer.score(), input.toString() + " result: ");
    assertEquals(input.status, bowlingScorer.status(), input.toString() + " status: ");
  }

}
