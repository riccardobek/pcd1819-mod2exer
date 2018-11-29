package pcd2018.lab1.bowling;

/**
 * Support class for parametrized tests.
 */
class StringVal {
  public final int score;
  public final String value;
  public final int strikes;
  public final int spares;

  StringVal(String value, int score, int strikes, int spares) {
    this.value = value;
    this.score = score;
    this.strikes = strikes;
    this.spares = spares;
  }
}
