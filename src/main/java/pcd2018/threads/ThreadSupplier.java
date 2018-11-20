package pcd2018.threads;

import java.util.function.Supplier;

/**
 * Supplies a Thread that waits a random time
 *
 */
public class ThreadSupplier implements Supplier<Thread> {

  /**
   * Produces the duration of the wait
   */
  Supplier<Long> waitTime;

  /**
   * A supplier of threads that wait a random time up to a second
   */
  public ThreadSupplier() {
    waitTime = () -> Math.round(Math.random() * 1000);
  }

  /**
   * A supplier of threads that all wait the same duration
   * 
   * @param wait duration to wait in millisecond
   */
  public ThreadSupplier(long wait) {
    waitTime = () -> wait;
  }

  @Override
  public Thread get() {
    return new Thread(() -> {
      String name = Thread.currentThread().getName();
      long time = waitTime.get();
      System.out.println(name + " will wait for " + time + " ms.");
      try {
        Thread.sleep(time);
        System.out.println(name + " is done wating for " + time + " ms.");
      } catch (InterruptedException e) {
        System.out.println(name + " has been interrupted!");
        e.printStackTrace();
      }
    });
  }

}