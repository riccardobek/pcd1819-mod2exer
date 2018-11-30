package pcd2018.threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Schedule some futures and get the first result.
 */
public class AnyFutures {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    FactorialBuilder supplier = new FactorialBuilder();
    ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();
    for (int i = 0; i < 10; i++)
      callables.add(supplier.get());

    System.out.println("Scheduling computations");
    Integer result = executor.invokeAny(callables);
    System.out.println("Done invoking: " + result);

    executor.shutdown();
  }

}
