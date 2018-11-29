package pcd2018.lab1.bowling;

import io.vavr.collection.List;

/**
 * Conserva il risultato via via che viene calcolato, fino ad arrivare al totale finale.
 */
class Score {

  public final int round, total, strikes, spares, gutters;
  public final List<Integer> rest;

  private Score(int round, int total, int strikes, int spares, int gutters, List<Integer> rest) {
    this.round = round;
    this.total = total;
    this.strikes = strikes;
    this.spares = spares;
    this.gutters = gutters;
    this.rest = rest;
  }

  public static Score start(List<Integer> pins) {
    return new Score(0, 0, 0, 0, 0, pins);
  }

  public boolean isStrike() {
    return rest.head() == 10;
  }

  public boolean isLast() {
    return round == 9;
  }

  public boolean isSpare() {
    return rest.length() > 1 && rest.head() + rest.tail().head() == 10;
  }

  public Score strike() {
    return new Score(round + 1, total + rest.head() + rest.tail().head() + rest.tail().tail().head(), strikes + 1,
        spares, gutters, rest.tail());
  }

  public Score spare() {
    int gut = (rest.head() == 0 || rest.tail().head() == 0) ? 1 : 0;
    return new Score(round + 1, total + rest.head() + rest.tail().head() + rest.tail().tail().head(), strikes,
        spares + 1, gutters + gut, rest.drop(2));
  }

  public Score normal() {
    int gut = (rest.head() == 0 ? 1 : 0) + (rest.tail().head() == 0 ? 1 : 0);
    return new Score(round + 1, total + rest.head() + rest.tail().head(), strikes, spares, gutters + gut, rest.drop(2));
  }

  public Score last() {
    int gut = (rest.head() == 0 ? 1 : 0) + (rest.tail().head() == 0 ? 1 : 0);
    if (rest.length() == 3)
      gut += (rest.tail().tail().head() == 0 ? 1 : 0);
    int spr = (rest.head() < 10 && (rest.head() + rest.tail().head() == 10) ? 1 : 0);
    int strk = (rest.head() == 10 ? 1 : 0) + (rest.tail().head() == 10 ? 1 : 0);
    if (rest.length() == 3)
      strk += (rest.tail().tail().head() == 10 ? 1 : 0);
    return new Score(round + 1, rest.foldLeft(total, (a, b) -> a + b), strikes + strk, spares + spr, gutters + gut,
        List.empty());
  }

  @Override
  public String toString() {
    return "(" + round + ":" + total + " (" + strikes + "," + spares + "," + gutters + ") " + rest + ")";
  }
}

/**
 * Calcola il risultato a partire dalla trascrizione della partita.
 *
 */
public class BowlingGame {

  public final Score score;

  /**
   * @param input Registrazione della partita
   */
  public BowlingGame(String input) {
    this(parse(input));
  }

  /**
   * @param vals Registrazione della partita sotto forma di lista di numeri
   */
  public BowlingGame(List<Integer> vals) {
    Score sc = Score.start(vals);
    do {
      if (sc.isLast())
        sc = sc.last();
      else if (sc.isStrike())
        sc = sc.strike();
      else if (sc.isSpare())
        sc = sc.spare();
      else
        sc = sc.normal();
      // System.out.println(sc);
    } while (!sc.rest.isEmpty());
    score = sc;
  }

  private static List<Integer> parse(String input) {
    List<Integer> res;
    try {
      res = parse(List.ofAll(input.replace('-', '0').toCharArray()), List.<Integer> empty());
    } catch (RuntimeException e) {
      throw new RuntimeException(e.getMessage() + " in " + input);
    }
    return res;
  }

  private static List<Integer> parse(List<Character> in, List<Integer> res) {
    if (in.isEmpty())
      return res;
    else if (in.length() > 1 && Character.isDigit(in.head()) && in.tail().head().charValue() == '/') {
      int first = in.head().charValue() - '0';
      int second = 10 - first;
      return parse(in.drop(2), res.append(first).append(second));
    } else if (in.head().charValue() == 'X')
      return parse(in.tail(), res.append(10));
    else if (Character.isDigit(in.head()))
      return parse(in.tail(), res.append(in.head().charValue() - '0'));
    else
      throw new RuntimeException("Illegal char '" + in.head() + "' (" + in.size() + ")");

  }

  /**
   * 
   * @return Punteggio finale
   */
  public int score() {
    return score.total;
  }

  /**
   * 
   * @return Numero di spares
   */
  public int spares() {
    return score.spares;
  }

  /**
   * 
   * @return Numero di strikes
   */
  public int strikes() {
    return score.strikes;
  }

  /**
   * 
   * @return Numero di gutters
   */
  public int gutters() {
    return score.gutters;
  }
}
