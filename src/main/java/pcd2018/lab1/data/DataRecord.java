package pcd2018.lab1.data;

/**
 * Record di dati da riassumere
 */
public class DataRecord {

  public final String key;
  public final int score, strikes, spares, gutters;

  public DataRecord(String key, int score, int strikes, int spares, int gutters) {
    this.key = key;
    this.score = score;
    this.strikes = strikes;
    this.spares = spares;
    this.gutters = gutters;
  }
}
