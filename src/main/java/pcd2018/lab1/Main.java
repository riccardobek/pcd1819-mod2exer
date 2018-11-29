package pcd2018.lab1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import pcd2018.lab1.data.FinalRecord;

public class Main {

  public static void main(String[] args) {
    // Preparazione dei componenti

    // TODO...

    // Lettura dei file di input

    Stream<String> filenames = Stream
        .of("source-a.xz", "source-b.xz", "source-c.xz", "source-d.xz", "source-e.xz", "source-f.xz", "source-g.xz")
        .map(s -> "src/main/resources/" + s);

    // TODO...

    // Trasformazione dei record letti

    // TODO...

    // Sommatoria secondo giocatori e piste

    Summarizer topPlayersSum = null;
    Summarizer topVenuesSum = null;

    // TODO...

    // Calcolo:
    // Top 10 giocatori (punteggio medio,partite giocate, strike totali, punteggio totale)
    // Top 10 piste (partite giocate, punteggio medio, strike totali, punteggio totale)

    System.out.println("Top players counting.");
    TreeSet<FinalRecord> strikes = entrydump(topPlayersSum.results().entrySet(), FinalRecord.AVGSTRIKES);
    System.out.println("Top venues counting.");
    TreeSet<FinalRecord> venues = entrydump(topVenuesSum.results().entrySet(), FinalRecord.AVGSCORE);
    entryprint(strikes, 10);
    System.out.println();
    entryprint(venues, 10);

    System.out.println("Games by players: " + entrySum(topPlayersSum.results().entrySet()));
    System.out.println("Games by venues: " + entrySum(topVenuesSum.results().entrySet()));

  }

  /**
   * Calcola il totale delle partite giocate.
   * 
   * @param set
   * @return
   */
  private static int entrySum(Set<Entry<String, int[]>> set) {
    int res = 0;
    for (Entry<String, int[]> e : set)
      res += e.getValue()[0];
    return res;
  }

  /**
   * Raccoglie tutti i risultati da una sorgente, li ordina e ritorna l'insieme ordinato.
   * 
   * @param source Sorgente di dati
   * @param comparator Comparatore
   * @return Elenco ordinato dei risultati
   */
  static TreeSet<FinalRecord> entrydump(Set<Entry<String, int[]>> source, Comparator<FinalRecord> comparator) {
    TreeSet<FinalRecord> res = new TreeSet<FinalRecord>(comparator);
    for (Entry<String, int[]> e : source) {
      int[] val = e.getValue();
      res.add(new FinalRecord(e.getKey(), val[0], val[1], val[2], val[3], val[4]));
    }
    return res;
  }

  /**
   * Stampa dei record in modo leggibile.
   * 
   * @param source Sorgente dei dati
   * @param amount Quanti record stampare
   */
  static void entryprint(TreeSet<FinalRecord> source, int amount) {
    Iterator<FinalRecord> iterator = source.iterator();
    for (int i = 0; i < amount && iterator.hasNext(); i++) {
      FinalRecord next = iterator.next();
      System.out.println(String.format("%d. %s %6d %6d %5d %5d %5d %7.5f %7.5f %7.5f %7.5f", i + 1, next.key,
          next.totgames, next.totscore, next.totstrikes, next.totspares, next.totgutters, next.avgscore,
          next.avgstrikes, next.avgspares, next.avggutters));
    }
  }

}
