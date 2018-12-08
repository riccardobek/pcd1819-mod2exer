package pcd2018.streams;

import java.util.stream.IntStream;

public class StringCollector {
  public static void main(String[] args) {
    String res = IntStream.range(1, 1001).boxed().map((i) -> i.toString()).parallel()
        // supplier
        .collect(() -> new StringBuffer(),
            // accumulator
            (acc, el) -> acc.append(el),
            // combiner
            (resA, resB) -> resA.append(resB))
        .toString();
    System.out.println(">>>> " + res);
  }

}
