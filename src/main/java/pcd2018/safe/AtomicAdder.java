package pcd2018.safe;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAdder {

  public AtomicInteger target = new AtomicInteger(0);

  public void add() {

    var t1 = new Thread(() -> {
      for (int i = 0; i < 100000; i++) {
        target.incrementAndGet();
      }
    });
    var t2 = new Thread(() -> {
      for (int i = 0; i < 100000; i++) {
        target.incrementAndGet();
      }
    });
    var t3 = new Thread(() -> {
      for (int i = 0; i < 100000; i++) {
        target.incrementAndGet();
      }
    });
    t1.start();
    t2.start();
    t3.start();
  }
}
