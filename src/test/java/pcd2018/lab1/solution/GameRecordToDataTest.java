package pcd2018.lab1.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pcd2018.lab1.bowling.GameRecord;
import pcd2018.lab1.data.DataRecord;

public class GameRecordToDataTest {

  @Test
  @Disabled
  public void test() {
    GameRecordToData gameRecordToPlayer = new GameRecordToData(GameRecord::player);
    GameRecordToData gameRecordToVenueLane = new GameRecordToData((GameRecord r) -> {
      return GameRecord.venue(r) + "-" + GameRecord.lane(r);
    });
    GameRecord record = new GameRecord("Jeffrey Leboski", "Hollywood Star Lanes", 4, "8/9/72XXX3/819/XX5");

    DataRecord player = gameRecordToPlayer.apply(record);
    DataRecord venueLane = gameRecordToVenueLane.apply(record);

    assertEquals("Jeffrey Leboski", player.key, "Player");
    assertEquals(190, player.score, "Player score");
    assertEquals("Hollywood Star Lanes-4", venueLane.key, "Venue Lane");
    assertEquals(190, venueLane.score, "Venue Lane");
  }

}
