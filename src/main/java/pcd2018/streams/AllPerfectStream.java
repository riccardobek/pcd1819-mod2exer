package pcd2018.streams;

import java.util.stream.IntStream;

public class AllPerfectStream {
  public static void main(String[] args) {
    IntStream.range(10, 10000).boxed().parallel().map(new Divisors()).filter(new Perfect())
        .forEach((CandidateNumber c) -> {
          System.out.println(">>> " + c.toString());
        });
  }
}
