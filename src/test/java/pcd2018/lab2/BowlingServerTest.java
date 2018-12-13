package pcd2018.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BowlingServerTest {

  @Test
  @Tag("Bowling")
  public void scorerTest() throws InterruptedException {
    BlockingQueue<Result> queue = new LinkedBlockingQueue<Result>(100);
    ExecutorService service = Executors.newFixedThreadPool(1);
    ScorePrinter printer = new ScorePrinter(queue);
    service.execute(printer);
    queue.put(new Result(1, 8));
    queue.put(new Result(1, 2));
    queue.put(new Result(1, 3));
    queue.put(new Result(1, 5));
    queue.put(new Result(2, 1));
    queue.put(new Result(1, 6));
    queue.put(new Result(2, 9));
    queue.put(new Result(2, 8));
    Thread.sleep(1000);
    service.shutdownNow();

    assertEquals(2, printer.games.size());
    assertEquals(5, printer.games.get(Integer.valueOf(1)).status().length());
    assertEquals(3, printer.games.get(Integer.valueOf(2)).status().length());
  }

}
