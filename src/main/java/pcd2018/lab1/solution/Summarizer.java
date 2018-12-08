package pcd2018.lab1.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import pcd2018.lab1.data.DataRecord;

/**
 * Raccoglie gli elementi da una coda, e li somma raggruppandoli per chiave.
 */
public class Summarizer implements Runnable, Consumer<DataRecord> {

  public static final int TOTAL_GAMES = 0;
  public static final int TOTAL_SCORE = 1;
  public static final int TOTAL_STRIKES = 2;
  public static final int TOTAL_SPARES = 3;
  public static final int TOTAL_GUTTERS = 4;
  public static final int MIN_SCORE = 5;
  public static final int MAX_SCORE = 6;

  private BlockingQueue<DataRecord> queue;
  private Map<String, int[]> results;
  private boolean done = false;

  public Summarizer(BlockingQueue<DataRecord> queue) {
    this.queue = queue;
    this.results = new HashMap<String, int[]>();
  }

  @Override
  public void run() {
    // se segnalato e la coda è vuota, concludi
    while (!done) {
      // attendi elementi sulla coda
      try {
        DataRecord record = queue.poll(1, TimeUnit.SECONDS);
        if (record != null)
          accept(record);
      } catch (InterruptedException e) {
        System.out.println("Summarizer interrupted with " + results.size() + " results remaining.");
        break;
      }
    }
    System.out.println("Summarizer draining: " + queue.size() + " results.");
    queue.forEach(this);
    System.out.println("Summarizer done.");
  }

  @Override
  public void accept(DataRecord record) {
    // trova l'elemento fra i risultati
    if (results.containsKey(record.key)) {
      // somma i dati al record se esistente
      int[] data = results.get(record.key);
      data[TOTAL_GAMES] += 1;
      data[TOTAL_SCORE] += record.score;
      data[TOTAL_STRIKES] += record.strikes;
      data[TOTAL_SPARES] += record.spares;
      data[TOTAL_GUTTERS] += record.gutters;
      data[MIN_SCORE] = (data[MIN_SCORE] > record.score) ? record.score : data[MIN_SCORE];
      data[MAX_SCORE] = (data[MAX_SCORE] < record.score) ? record.score : data[MAX_SCORE];
    } else {
      // crea un nuovo record di risultato se non esistente
      int[] data = new int[] {
        1,
        record.score,
        record.strikes,
        record.spares,
        record.gutters,
        record.score,
        record.score };
      results.put(record.key, data);
    }
  }

  /**
   * Ottieni i risultati
   * 
   * @return
   */
  public Map<String, int[]> results() {
    return results;
  }

  public void done() {
    this.done = true;
  }
}
