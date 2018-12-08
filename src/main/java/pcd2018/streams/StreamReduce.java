package pcd2018.streams;

import java.util.stream.IntStream;

public class StreamReduce {
  public static void main(String[] args) {
    int res = IntStream.range(1, 1001).reduce(0, (a, b) -> {
      System.out.println(a + "+" + b + "=" + (a + b) + " " + Thread.currentThread().getName());
      return a + b;
    });
    System.out.println(">>>> " + res);
  }
}
