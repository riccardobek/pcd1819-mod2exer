package pcd2018.safe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Holds a volatile variable to be manupulated by external threads.
 */
class VolatileHolder {
  volatile int counter = 0;
}

/**
 * This test will, sometimes, succeed. But usually it will fail. See Lesson 3.
 */
public class VolatileTest {

  @Test
  @Disabled
  public void volatileCounter() {
    // Target data
    VolatileHolder holder = new VolatileHolder();

    // Set up some task that increment the shared counter
    ExecutorService executor = Executors.newFixedThreadPool(4);
    IntStream.range(0, 10000).forEach(i -> executor.submit(() -> holder.counter++));
    awaitDone(executor);

    // have all the task operated correctly?
    assertEquals(10000, holder.counter);
  }

  /**
   * Wait for an executor service to end all the tasks that have been enqueued
   * 
   * @param executor The executor to shutdown gracefully
   */
  static void awaitDone(ExecutorService executor) {
    executor.shutdown();
    while (!executor.isTerminated())
      try {
        System.out.print(".");
        executor.awaitTermination(1, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
  }
}
