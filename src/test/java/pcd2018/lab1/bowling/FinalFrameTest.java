package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Check final frame logic.
 */
public class FinalFrameTest {

  public static List<StringVal> finalFrame() {
    return Arrays.asList(new StringVal("1/35XXX458/X3/23", 160, 4, 3), new StringVal("1/35XXX458/X3/XX6", 189, 6, 3),
        new StringVal("1/35XXX458/X35", 149, 4, 2), new StringVal("1/35XXX458/X3/X--", 173, 5, 3),
        new StringVal("XXXXXXXXXXXX", 300, 12, 0), new StringVal("XXXXXXXXXX12", 274, 10, 0),
        new StringVal("1/35XXX458/X3/--", 153, 4, 3), new StringVal("5/5/5/5/5/5/5/5/5/5/5", 150, 0, 10),
        new StringVal("9-8/--9-9-9-9-9-9-9/1", 84, 0, 2), new StringVal("9-X8-9-9-9-9-9-9-X23", 104, 2, 0));
  }

  @ParameterizedTest
  @MethodSource("finalFrame")
  public void test(StringVal input) {
    BowlingGame res = new BowlingGame(input.value);
    assertEquals(input.score, res.score(), input.value);
    assertEquals(input.spares, res.spares(), input.value);
    assertEquals(input.strikes, res.strikes(), input.value);
  }
}
