package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Check the parsing of textual representation of the game.
 */
public class Textual {

  public static List<StringVal> textual() {
    return Arrays.asList(new StringVal("--", 0, 0, 0), new StringVal("1-", 1, 0, 0), new StringVal("13", 4, 0, 0),
        new StringVal("13521-", 12, 0, 0), new StringVal("1-5-", 6, 0, 0), new StringVal("1/--", 10, 0, 1),
        new StringVal("1/--", 10, 0, 1), new StringVal("1/-5", 15, 0, 1), new StringVal("1/35--", 21, 0, 1),
        new StringVal("1/3/23", 30, 0, 2), new StringVal("X--", 10, 1, 0), new StringVal("X--51", 16, 1, 0),
        new StringVal("X51", 22, 1, 0));
  }

  @ParameterizedTest
  @MethodSource("textual")
  public void test(StringVal input) {
    BowlingGame res = new BowlingGame(input.value);
    assertEquals(input.score, res.score(), input.value);
    assertEquals(input.spares, res.spares(), input.value);
    assertEquals(input.strikes, res.strikes(), input.value);
  }
}
