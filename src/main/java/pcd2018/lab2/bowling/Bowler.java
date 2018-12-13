package pcd2018.lab2.bowling;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Generatore di partite per un giocatore di livello indicato.
 */
public class Bowler {

  public final int level;
  private final Random random = new Random(new Date().getTime());

  /**
   * Crea un nuovo giocatore per il livello indicato.
   * 
   * @param level 1 per principiante, 9 per esperto. 10 massimo.
   */
  public Bowler(int level) {
    this.level = level;
  }

  /**
   * Produce una partita intera come stringa
   * 
   * @return il risultato di una partita giocata da questo giocatore
   */
  String game() {
    String frames = firstFrames();
    int tenth1 = firstThrow(), tenth2;
    Optional<Integer> tenth3;
    if (strike(tenth1)) {
      tenth2 = firstThrow();
      tenth3 = Optional.of(strike(tenth2) ? firstThrow() : secondThrow(tenth2));
    } else {
      tenth2 = secondThrow(tenth1);
      if (spare(tenth1, tenth2))
        tenth3 = Optional.of(firstThrow());
      else
        tenth3 = Optional.empty();
    }

    String last;
    if (strike(tenth1) && strike(tenth2) && strike(tenth3.get()))
      last = "XXX";
    else if (strike(tenth1) && spare(tenth2, tenth3.get()))
      last = "X" + tenth2 + "/";
    else if (strike(tenth1))
      last = "X" + tenth2 + tenth3.get();
    else if (spare(tenth1, tenth2) && strike(tenth3.get()))
      last = tenth1 + "/X";
    else if (spare(tenth1, tenth2))
      last = tenth1 + "/" + tenth3.get();
    else
      last = "" + tenth1 + tenth2;

    return frames + last.replace('0', '-');
  }

  /**
   * Produce una partita intera come elenco di tiri
   * 
   * @return il risultato di una partita giocata da questo giocatore
   */
  public List<Integer> pins() {
    List<Integer> frames = normalFrames();
    int tenth1 = firstThrow(), tenth2;
    Optional<Integer> tenth3;
    if (strike(tenth1)) {
      tenth2 = firstThrow();
      tenth3 = Optional.of(strike(tenth2) ? firstThrow() : secondThrow(tenth2));
    } else {
      tenth2 = secondThrow(tenth1);
      if (spare(tenth1, tenth2))
        tenth3 = Optional.of(firstThrow());
      else
        tenth3 = Optional.empty();
    }
    frames.add(tenth1);
    frames.add(tenth2);
    if (tenth3.isPresent())
      frames.add(tenth3.get());
    return frames;
  }

  private boolean strike(int pins) {
    return pins == 10;
  }

  private boolean spare(int pins1, int pins2) {
    return pins1 + pins2 == 10;
  }

  String firstFrames() {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 9; i++) {
      int first = firstThrow();
      if (first == 10)
        buf.append("X");
      else {
        int second = secondThrow(first);
        String firstChar = first == 0 ? "-" : first + "";
        String secondChar;
        if (second + first == 10)
          secondChar = "/";
        else
          secondChar = second == 0 ? "-" : second + "";
        buf.append(firstChar).append(secondChar);
      }
    }
    return buf.toString();
  }

  List<Integer> normalFrames() {
    List<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < 9; i++) {
      int first = firstThrow();
      res.add(first);
      if (first < 10) {
        int second = secondThrow(first);
        res.add(second);
      }
    }
    return res;
  }

  /**
   * Il primo tiro di ogni frame non è influenzato dai precedenti.
   * 
   * Probabilità: strike (lev*10)%, point (10-lev/10)
   * 
   * @return
   */
  int firstThrow() {
    int rand = random.nextInt(1000);
    int res;
    res = firstCalc(rand);
    return res;
  }

  int firstCalc(int rand) {
    int res;
    if (rand <= level * 100)
      res = 10;
    else
      res = (rand - level * 100) / ((11 - level) * 10);
    return res;
  }

  /**
   * Il secondo tiro è influenzato dal primo.
   * 
   * Probabilità: spare (lev*10)%, point(10-lev/10-first)
   * 
   * @param first
   * @return
   */
  int secondThrow(int first) {
    int rand = random.nextInt(1000);
    int res;
    res = secondCalc(first, rand);
    return res;
  }

  int secondCalc(int first, int rand) {
    int res;
    if (rand <= level * 100)
      res = 10 - first;
    else
      res = (int) Math.floor(1.0 * (11 - first) * (rand - level * 100) / ((11 - level) * 100));
    return res;
  }

}
