package pcd2018.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import pcd2018.lab1.data.DataRecord;

public class SummarizerTest {

  @Test
  @Tag("Step-4")
  public void test() throws InterruptedException {
    BlockingQueue<DataRecord> queue = new LinkedBlockingQueue<DataRecord>(15);
    queue.put(new DataRecord("Jeffrey Leboski", 190, 5, 4, 0));
    queue.put(new DataRecord("Jeffrey Leboski", 210, 6, 5, 0));
    queue.put(new DataRecord("Walter Sobchak", 130, 2, 4, 1));

    Summarizer runnable = new Summarizer(queue);

    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(runnable);
    Thread.sleep(500);
    executor.shutdownNow();
    Map<String, int[]> results = runnable.results();

    assertEquals(2, results.size(), "Size");

    int[] leboski = new int[] { 2, 400, 11, 9, 0, 190, 210 };
    int[] sobchak = new int[] { 1, 130, 2, 4, 1, 130, 130 };
    for (int idx = 0; idx < leboski.length; idx++) {
      assertEquals(leboski[idx], results.get("Jeffrey Leboski")[idx], "Leboski " + idx);
      assertEquals(sobchak[idx], results.get("Walter Sobchak")[idx], "Sobchak " + idx);
    }

  }

}
