package pcd2018.threads;

import java.util.stream.Stream;

public class ManyThreads {

  public static void main(String[] args) {
    Stream<Thread> threads = Stream.generate(new ThreadSupplier());

    System.out.println("Starting Threads");
    threads.limit(10).forEach((Thread a) -> a.start());
    System.out.println("Done starting.");

  }

}
