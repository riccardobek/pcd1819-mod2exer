package pcd2018.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Run multiple tasks on a pool containing a single thread.
 *
 */
public class SingleThreadPool {

  public static void main(String[] args) {
    Executor executor = Executors.newSingleThreadExecutor();

    Stream<Thread> threads = Stream.generate(new ThreadSupplier());
    System.out.println("Scheduling runnables");
    threads.limit(10).forEach((r) -> executor.execute(r));
    System.out.println("Done scheduling.");
  }

}
