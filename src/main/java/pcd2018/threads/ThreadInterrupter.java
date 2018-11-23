package pcd2018.threads;

/**
 * A thread that interrupts another one after one waiting one second, while reporting about the target liveness.
 *
 */
class Interrupter implements Runnable {

  private final Thread tgt;

  Interrupter(Thread tgt) {
    this.tgt = tgt;
  }

  @Override
  public void run() {
    System.out.println("Target Thread is live: " + tgt.isAlive());
    for (int i = 0; i < 4; i++) {
      try {
        Thread.sleep(1000L);
        System.out.println("Interrupting target thread");
        tgt.interrupt();
        System.out.println("Target interrupted.");
      } catch (InterruptedException e) {
        System.out.println("Interrupter Interrupted");
        e.printStackTrace();
      }
    }
    System.out.println("Target Thread alive: " + tgt.isAlive());
  }
}

/**
 * A Thread that interrupts another one.
 */
public class ThreadInterrupter {

  public static void main(String[] args) {

    // A thread that stays alive for a while
    final Thread tgt = new ThreadSupplier(2000L).get();

    // Another thread, that interrupts the target one
    final Thread interrupter = new Thread(new Interrupter(tgt));

    // Start both threads.
    interrupter.start();
    tgt.start();
  }

}
