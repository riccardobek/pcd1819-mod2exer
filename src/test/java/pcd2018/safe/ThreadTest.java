package pcd2018.safe;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import pcd2018.threads.ThreadSupplier;

/**
 * Test ThreadSupplier's functionality
 */
public class ThreadTest {

  @Test
  public void singleTest() {
    Thread a = new ThreadSupplier().get();

    try {
      System.out.println("----------------------\nStarting Single Thread");
      a.start();
      System.out.println("Done starting.");
    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  public void multiTest() {
    Stream<Thread> threads = Stream.generate(new ThreadSupplier());

    try {
      System.out.println("Starting Threads");
      threads.limit(10).forEach((Thread a) -> a.start());
      System.out.println("Done starting.");
    } catch (Exception e) {
      fail(e);
    }
  }
}
