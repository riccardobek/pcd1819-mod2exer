package pcd2018.lab1;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pcd2018.lab1.data.DataRecord;

/**
 * Raccoglie gli elementi da una coda, e li somma raggruppandoli per chiave.
 */
public class Summarizer implements Runnable {

  public static final int TOTAL_GAMES = 0;
  public static final int TOTAL_SCORE = 1;
  public static final int TOTAL_STRIKES = 2;
  public static final int TOTAL_SPARES = 3;
  public static final int TOTAL_GUTTERS = 4;
  public static final int MIN_SCORE = 5;
  public static final int MAX_SCORE = 6;

  private BlockingQueue<DataRecord> queue;
  private Map<String, int[]> results;

  public Summarizer(BlockingQueue<DataRecord> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    // FIXME attendi elementi sulla coda
    // FIXME trova l'elemento fra i risultati
    // FIXME crea un nuovo record di risultato se non esistente
    // FIXME somma i dati al record se esistente
  }

  /**
   * Ottieni i risultati
   * 
   * @return
   */
  public Map<String, int[]> results() {
    return results;
  }

}
