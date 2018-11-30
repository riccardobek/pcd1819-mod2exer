package pcd2018.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Schedule some futures and ask for their complete execution.
 */
public class AllFutures {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    FactorialBuilder supplier = new FactorialBuilder();
    ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();
    for (int i = 0; i < 10; i++)
      callables.add(supplier.get());

    System.out.println("Scheduling computations");
    List<Future<Integer>> futures = executor.invokeAll(callables);
    System.out.println("Done computations");

    while (executor.getCompletedTaskCount() < futures.size()) {
      System.out.printf("Completed Tasks: %d: %s\n", executor.getCompletedTaskCount(), format(futures));
      TimeUnit.MILLISECONDS.sleep(50);
    }

    System.out.printf("Completed Tasks: %d: %s\n", executor.getCompletedTaskCount(), format(futures));

    executor.shutdown();
  }

  private static String format(List<Future<Integer>> futures) throws InterruptedException, ExecutionException {
    StringBuilder done = new StringBuilder();
    for (Future<Integer> future : futures) {
      if (future.isDone())
        done.append(String.format("%6d", future.get()));
      else
        done.append(" _____");
    }
    return done.toString();
  }

}
