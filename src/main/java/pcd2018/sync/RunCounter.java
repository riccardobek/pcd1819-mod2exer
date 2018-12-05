package pcd2018.sync;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunCounter {
  public static void main(String[] args) throws InterruptedException, ExecutionException {

    ExecutorService executor = Executors.newFixedThreadPool(1);
    // ExecutorService executor = Executors.newFixedThreadPool(4);
    // ExecutorService executor = Executors.newSingleThreadExecutor();

    SimpleCounter counter = new SyncCounter();
    List<Incrementer> incs = Arrays.asList(new Incrementer(counter), new Incrementer(counter), new Incrementer(counter),
        new Incrementer(counter));
    long time = System.currentTimeMillis();
    System.out.println("Running...");
    executor.invokeAll(incs);
    long end = System.currentTimeMillis();
    System.out.println("All done. Final state: " + counter.getState() + " (" + (end - time) + ")");
  }
}
