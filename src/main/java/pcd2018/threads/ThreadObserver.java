package pcd2018.threads;

/**
 * A Thread that observes another one's liveness.
 */
public class ThreadObserver {

  public static void main(String[] args) {

    // A thread that stays alive for a while
    final Thread tgt = new ThreadSupplier(800L).get();

    // Another thread, that checks if target is alive
    final Thread observer = new Thread(() -> {
      System.out.println("Target Thread is live: " + tgt.isAlive());
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(100L);
          System.out.println("Target Thread is live: " + tgt.isAlive());
        } catch (InterruptedException e) {
          System.out.println("Observer Interrupted");
          e.printStackTrace();
        }
      }
      System.out.println("Target Thread is live: " + tgt.isAlive());
    });

    // Start both threads.
    observer.start();
    tgt.start();
  }

}
