package pcd2018.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import pcd2018.safe.Printer;

public class PrintQueue implements Printer {
  private final Lock queueLock = new ReentrantLock();

  public void printJob(Object document) {
    queueLock.lock();
    try {
      Long duration = (long) (Math.random() * 10000);
      System.out.println(
          Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      queueLock.unlock();
    }
  }
}
