package pcd2018.lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pcd2018.lab2.bowling.BowlingScorer;

/**
 * Single result read from the network, that should be added to the score.
 */
class Result {
  final Integer lane;
  final Integer pins;

  Result(int lane, int pins) {
    this.lane = lane;
    this.pins = pins;
  }
}

/**
 * Reads results from the queue and mantains a map of active games.
 * 
 * Can init new games when needed. Prints out status of every modified game.
 */
class ScorePrinter implements Runnable {

  private BlockingQueue<Result> inputQueue;
  Map<Integer, BowlingScorer> games = new HashMap<Integer, BowlingScorer>();

  ScorePrinter(BlockingQueue<Result> inputQueue) {
    this.inputQueue = inputQueue;
  }

  @Override
  public void run() {
    // finché non veniamo interrotti:
    // prendi un risultato dalla coda
    // aggiungilo alla partita cui appartiene
    // crea la partita se non esiste
    // quando veniamo interrotti:
    // stampa tutte le partite osservate
  }
}

/**
 * Reads a packet from network as "<lane>:<score>" and puts in queue to be summed up.
 */
public class BowlingServer {

  /**
   * Port where to listen to incoming messages, and where clients should send them.
   */
  static final int GAME_PORT = 56423;

  public static void main(String args[]) {
    // con un socket per Datagram opportunamente predisposto:
    // prepara un pacchetto per ricevere i messaggi
    // istanzia ed avvia il sommatore
    // ripeti:
    // ricevi un messaggio
    // interpretalo ed ottieni un risultato da sommare
    // inoltralo al sommatore
  }
}
