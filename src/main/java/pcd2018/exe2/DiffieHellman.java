package pcd2018.exe2;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Classe da completare per l'esercizio 2.
 */
public class DiffieHellman {

  /**
   * Limite massimo dei valori segreti da cercare
   */
  private static final int LIMIT = 65536;

  private final long p;
  private final long g;

  public DiffieHellman(long p, long g) {
    this.p = p;
    this.g = g;
  }

  /**
   * Metodo da completare
   * 
   * @param publicA valore di A
   * @param publicB valore di B
   * @return tutte le coppie di possibili segreti a,b
   */
  public List<Integer> crack(long publicA, long publicB) {
    List<Integer> res = new ArrayList<>();

    HashMap<Integer, Long> alice = new HashMap<>(
      IntStream.rangeClosed(0, LIMIT)
               .parallel()
               .boxed()
               .collect(Collectors.toMap(x -> x, x -> DiffieHellmanUtils.modPow(publicB, x, p)))
      );

    HashMap<Integer, Long> bob = new HashMap<>(
      IntStream.rangeClosed(0, LIMIT)
               .parallel()
               .boxed()
               .collect(Collectors.toMap(x -> x, x -> DiffieHellmanUtils.modPow(publicA, x, p)))
      );

    alice.entrySet()
            .parallelStream()
            .forEach( x -> {
              Integer[] values = bob.entrySet()
                          .parallelStream()
                          .filter( y -> y.getValue().equals(x.getValue()))
                          .map(Map.Entry::getKey)
                          .toArray(Integer[]::new);

              Arrays.stream(values)
                    .parallel()
                    .sorted()
                    .forEach(val -> {
                      synchronized (this) {
                        res.add(x.getKey());
                        res.add(val);}
                    });
            });

    return res;
  }
}
