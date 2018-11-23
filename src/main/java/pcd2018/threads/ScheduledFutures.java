package pcd2018.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Schedule some futures and wait for their execution.
 */
public class ScheduledFutures {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    Supplier<Callable<Integer>> supplier = new FactorialBuilder();
    List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

    System.out.println("Scheduling computations");

    for (int i = 0; i < 10; i++)
      futures.add(executor.submit(supplier.get()));

    System.out.println("Done scheduling.");

    while (executor.getCompletedTaskCount() < futures.size()) {
      System.out.printf("Completed Tasks: %2d: %s\n", executor.getCompletedTaskCount(), format(futures));
      TimeUnit.MILLISECONDS.sleep(50);
    }

    System.out.printf("Completed Tasks: %2d: %s\n", executor.getCompletedTaskCount(), format(futures));

    executor.shutdown();
  }

  /**
   * Format the actual status of the Futures
   * 
   * @param futures list of futures to report on
   * @return the status line to print
   * @throws InterruptedException
   * @throws ExecutionException
   */
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
