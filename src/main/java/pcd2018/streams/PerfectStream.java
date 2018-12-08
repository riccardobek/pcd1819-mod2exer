package pcd2018.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerfectStream {
  public static void main(String[] args) {
    List<CandidateNumber> match = IntStream.range(30, 100000).boxed().parallel().map(new Divisors())
        .map((CandidateNumber c) -> {
          // System.out.println(c.toString());
          return c;
        }).filter(new Perfect()).findAny().stream().collect(Collectors.toList());
    // try removing .findAny().stream()
    match.forEach(x -> {
      System.out.println(x);
    });
  }
}
