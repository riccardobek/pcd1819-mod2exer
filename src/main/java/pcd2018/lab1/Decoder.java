package pcd2018.lab1;

import java.util.concurrent.BlockingQueue;

import pcd2018.lab1.bowling.GameRecord;
import pcd2018.lab1.data.DataRecord;

/**
 * Riceve record dalla coda di input, e li smista alle due code di output dopo averli trasformati.
 */
public class Decoder implements Runnable {

  /**
   * Costruisce il componente
   * 
   * @param source Coda sorgente
   * @param transformer1 Trasformatore per i dati del primo risultato
   * @param dest1 Coda di destinazione per il primo risultato
   * @param transformer2 Trasformatore per i dati del secondo risultato
   * @param dest2 Coda di destinazione per il secondo risultato
   */
  Decoder(BlockingQueue<GameRecord> source, GameRecordToData transformer1, BlockingQueue<DataRecord> dest1,
    GameRecordToData transformer2, BlockingQueue<DataRecord> dest2) {

  }

  /**
   * Attende un record dalla coda di ingresso, lo trasforma e lo passa alle code di uscita.
   */
  @Override
  public void run() {
    // TODO
  }

}
