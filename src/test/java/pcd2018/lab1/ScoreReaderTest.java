package pcd2018.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import pcd2018.lab1.bowling.GameRecord;

public class ScoreReaderTest {

  @Test
  @Tag("Step-1")
  public void testParse() {
    String input = "Jeffrey Leboski|Hollywood Star Lanes|4|8/9/72XXX3/819/XX5";
    GameRecord gameRecord = ScoreReader.parse(input);
    assertEquals("Jeffrey Leboski", gameRecord.player, "Name");
    assertEquals("Hollywood Star Lanes", gameRecord.venue, "Venue");
    assertEquals(4, gameRecord.lane, "Lane");
    assertEquals("8/9/72XXX3/819/XX5", gameRecord.score, "Score");
  }

  @Test
  @Tag("Step-2")
  public void testRead() throws FileNotFoundException, IOException {
    ScoreReader reader = new ScoreReader("src/test/resources/scoreReader.test.xz");

    GameRecord rec1 = reader.get();
    assertEquals("Sojie Eckel", rec1.player, "Name");
    assertEquals("Houston Ball&Pins", rec1.venue, "Venue");
    assertEquals(7, rec1.lane, "Lane");
    assertEquals("4-8/354-5235818/8/2-", rec1.score, "Score");

    GameRecord rec2 = reader.get();
    assertEquals("Sears Odette", rec2.player, "Name");
    assertEquals("Chicago Pins", rec2.venue, "Venue");
    assertEquals(1, rec2.lane, "Lane");
    assertEquals("XX325-XX7/7/4-XXX", rec2.score, "Score");
  }

}
