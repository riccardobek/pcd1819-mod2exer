package pcd2018.exe2;

import javax.naming.LimitExceededException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collector;
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

    HashMap<Integer,Long> alice = new HashMap<>(
        IntStream.rangeClosed(0, LIMIT)
            .parallel()
            .boxed()
            .collect(Collectors.toMap(i -> i, i -> DiffieHellmanUtils.modPow(publicB, i, p)))
      );

    HashMap<Integer,Long> bob = new HashMap<>(
        IntStream.rangeClosed(0, LIMIT)
            .parallel()
            .boxed()
            .collect(Collectors.toMap(j -> j, j -> DiffieHellmanUtils.modPow(publicA, j, p)))
    );

    alice.entrySet().parallelStream().forEach(
        i-> bob.entrySet()
                .parallelStream()
                .filter(j -> j.getValue().equals(i.getValue()))
                .map(Map.Entry::getKey)
                .forEach(
                        value -> {
                            synchronized (this){
                                res.add(i.getKey());
                                res.add(value);
                            }
                        })
        );

    return res;
  }
}
