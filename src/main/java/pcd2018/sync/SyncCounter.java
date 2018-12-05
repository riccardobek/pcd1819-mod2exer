package pcd2018.sync;

import java.util.concurrent.TimeUnit;

/**
 * A syncronized counter: only one thread can increment at a time.
 */
class SyncCounter implements SimpleCounter {
  private int state = 0;

  synchronized public void add() {
    int current = state;
    System.out.println("current " + current);
    try {
      TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 100));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    state = current + 1;
    System.out.println("added up to " + state);
  }

  public int getState() {
    return state;
  }
}