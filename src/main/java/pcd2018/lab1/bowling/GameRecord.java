package pcd2018.lab1.bowling;

/**
 * Informazioni riguardo una singola partita di bowling
 */
public class GameRecord {

  public final String player, venue, score;
  public final int lane;

  /**
   * 
   * @param player Nome del giocatore
   * @param venue Luogo della partita
   * @param lane Pista usata
   * @param score Punteggio
   */
  public GameRecord(String player, String venue, int lane, String score) {
    this.player = player;
    this.venue = venue;
    this.lane = lane;
    this.score = score;
  }

  public static String player(GameRecord r) {
    return r.player;
  }

  public static String venue(GameRecord r) {
    return r.venue;
  }

  static public int lane(GameRecord r) {
    return r.lane;
  }
}
