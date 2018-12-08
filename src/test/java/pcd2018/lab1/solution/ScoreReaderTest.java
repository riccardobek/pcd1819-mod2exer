package pcd2018.lab1.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pcd2018.lab1.bowling.GameRecord;

public class ScoreReaderTest {

  @Test
  @Disabled
  public void testParse() {
    String input = "Jeffrey Leboski|Hollywood Star Lanes|4|8/9/72XXX3/819/XX5";
    GameRecord gameRecord = ScoreReader.parse(input);
    assertEquals("Jeffrey Leboski", gameRecord.player, "Name");
    assertEquals("Hollywood Star Lanes", gameRecord.venue, "Venue");
    assertEquals(4, gameRecord.lane, "Lane");
    assertEquals("8/9/72XXX3/819/XX5", gameRecord.score, "Name");

    System.out.println(">>>>>");
  }

}
