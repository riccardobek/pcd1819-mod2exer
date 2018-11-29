package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test normal game logic, without too many extras.
 */
public class NormalPlay {

  public static List<StringVal> normalPlays() {
    return Arrays.asList(new StringVal("1/35XXX45", 103, 3, 1), new StringVal("11111111112222222222", 30, 0, 0),
        new StringVal("--------------------", 0, 0, 0), new StringVal("1-1----------------1", 3, 0, 0),
        new StringVal("9-9-9-9-9-9-9-9-9-9-", 90, 0, 0), new StringVal("5/11------------3/11", 26, 0, 2),
        new StringVal("9-8/--9-9-9-9-9-9-9-", 82, 0, 1), new StringVal("--8/1---------------", 12, 0, 1),
        new StringVal("--8/-1--------------", 11, 0, 1), new StringVal("9-X8-9-9-9-9-9-9-9-", 98, 1, 0),
        new StringVal("--X81--------------", 28, 1, 0), new StringVal("--X8-1-------------", 27, 1, 0),
        new StringVal("8/9/72XXX3/819/XX5", 190, 5, 4), new StringVal("X3/4/6-X-1-/-/X54", 126, 3, 4));
  }

  @ParameterizedTest
  @MethodSource("normalPlays")
  public void test(StringVal input) {
    BowlingGame res = new BowlingGame(input.value);
    assertEquals(input.score, res.score(), input.value);
    assertEquals(input.spares, res.spares(), input.value);
    assertEquals(input.strikes, res.strikes(), input.value);
  }
}
