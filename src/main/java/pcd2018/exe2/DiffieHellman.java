package pcd2018.exe2;

import java.util.ArrayList;
import java.util.List;

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
    List<Integer> res = new ArrayList<Integer>();
/*
    for (long i=0;i<LIMIT;++i){
      long alice = DiffieHellmanUtils.modPow(publicA,i,p);
      for (long j=0;j<LIMIT;++j){
        long bob = DiffieHellmanUtils.modPow(publicB,j,p);
        if(alice==bob){
          res.add((int)i);
          res.add((int)j);
        }
      }
    }
*/
    return res;
  }
}
