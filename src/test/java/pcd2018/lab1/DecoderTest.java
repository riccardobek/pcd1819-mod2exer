package pcd2018.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.vavr.collection.HashSet;
import pcd2018.lab1.bowling.GameRecord;
import pcd2018.lab1.data.DataRecord;

class DecoderTest {

  @Test
  @Tag("Step-3")
  void test() throws InterruptedException {

    BlockingQueue<GameRecord> source = new LinkedBlockingQueue<GameRecord>(15);
    GameRecordToData transformer1 = new GameRecordToData(GameRecord::player);
    BlockingQueue<DataRecord> dest1 = new LinkedBlockingQueue<DataRecord>(15);
    GameRecordToData transformer2 = new GameRecordToData(GameRecord::venue);
    BlockingQueue<DataRecord> dest2 = new LinkedBlockingQueue<DataRecord>(15);

    Decoder decoder = new Decoder(source, transformer1, dest1, transformer2, dest2);

    source.put(new GameRecord("Jeffrey Leboski", "Hollywood Star Lanes", 4, "8/9/72XXX3/819/XX5"));
    source.put(new GameRecord("Sojie Eckel", "Houston Ball&Pins", 7, "4-8/354-5235818/8/2-"));
    source.put(new GameRecord("Sears Odette", "Chicago Pins", 1, "XX325-XX7/7/4-XXX"));

    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(decoder);
    Thread.sleep(500);
    executor.shutdownNow();

    assertEquals(source.size(), 0, "Source");
    assertEquals(dest1.size(), 3, "Destination 1");
    assertEquals(dest2.size(), 3, "Destinazion 2");
    assertEquals(HashSet.<DataRecord> ofAll(dest1).map(r -> r.key),
        HashSet.of("Jeffrey Leboski", "Sojie Eckel", "Sears Odette"), "Queue 1");
    assertEquals(HashSet.<DataRecord> ofAll(dest2).map(r -> r.key),
        HashSet.of("Hollywood Star Lanes", "Houston Ball&Pins", "Chicago Pins"), "Queue 2");
  }

}
