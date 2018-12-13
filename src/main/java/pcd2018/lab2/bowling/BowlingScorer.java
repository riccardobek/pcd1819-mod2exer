package pcd2018.lab2.bowling;

import io.vavr.collection.List;

interface Play {
  /**
   * Aggiunge il risultato ottenuto, e ritorna il nuovo risultato che sostituisce l'attuale ed eventualmente il nuovo
   * passo aperto.
   * 
   * @param pin
   * @return
   */
  List<Play> pin(List<Play> current, int pin);

  /**
   * Ritorna la stringa che rappresenta questo frame
   * 
   * @return
   */
  String status();

  /**
   * Ritorna il punteggio per questo frame.
   * 
   * @param next
   * @return
   */
  int score(List<Play> next);

}

class EmptyFrame implements Play {

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    if (pin < 10)
      return List.of(IncompleteFrame.pin(pin));
    else if (pin == 10 && current.size() < 9)
      return List.of(new StrikeFrame(), empty());
    else if (pin == 10 && current.size() == 9)
      return List.of(new StrikeFrame(), LastEmptyFrame.last());
    else // shouldn't happen
      return List.empty();
  }

  @Override
  public int score(List<Play> next) {
    return 0;
  }

  @Override
  public String status() {
    return "";
  }

  static Play empty() {
    return new EmptyFrame();
  }

}

class IncompleteFrame implements Play {
  int pin;

  IncompleteFrame(int pin) {
    this.pin = pin;
  }

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    List<Play> result;
    if (this.pin + pin < 10 && current.size() < 9)
      result = List.of(NormalFrame.frame(this.pin, pin), EmptyFrame.empty());
    else if (this.pin + pin < 10 && current.size() == 9)
      result = List.of(NormalFrame.frame(this.pin, pin), LastEmptyFrame.last());
    else if (this.pin + pin == 10 && current.size() < 9)
      result = List.of(SpareFrame.spare(this.pin), EmptyFrame.empty());
    else if (this.pin + pin == 10 && current.size() == 9)
      result = List.of(SpareFrame.spare(this.pin), LastEmptyFrame.last());
    else // shouldn't happen
      result = List.empty();
    return result;
  }

  public int score(List<Play> next) {
    return pin;
  }

  public String status() {
    return pin > 0 ? "" + pin : "-";
  }

  static Play pin(int pin) {
    return new IncompleteFrame(pin);
  }
}

class NormalFrame implements Play {
  int pin1;
  int pin2;

  NormalFrame(int pin1, int pin2) {
    this.pin1 = pin1;
    this.pin2 = pin2;
  }

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    // normal frame is a completed frame it should not receive new pins
    return List.empty();
  }

  public int score(List<Play> next) {
    return pin1 + pin2;
  }

  public String status() {
    return (pin1 > 0 ? "" + pin1 : "-") + (pin2 > 0 ? "" + pin2 : "-");
  }

  static Play frame(int pin1, int pin2) {
    return new NormalFrame(pin1, pin2);
  }
}

class SpareFrame implements Play {
  int pin;

  SpareFrame(int pin) {
    this.pin = pin;
  }

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    // spare frame is a completed frame it should not receive new pins
    return List.empty();
  }

  public int score(List<Play> next) {
    int res = 10;
    if (!next.isEmpty()) {
      Play frame = next.iterator().next();
      if (frame instanceof IncompleteFrame)
        res = res + ((IncompleteFrame) frame).pin;
      if (frame instanceof NormalFrame)
        res = res + ((NormalFrame) frame).pin1;
      if (frame instanceof SpareFrame)
        res = res + ((SpareFrame) frame).pin;
      if (frame instanceof StrikeFrame)
        res = res + 10;
      if (frame instanceof LastThreeFrame)
        res = res + ((LastThreeFrame) frame).pin1;
    }
    return res;
  }

  public String status() {
    return (pin > 0 ? "" + pin : "-") + "/";
  }

  static Play spare(int pin) {
    return new SpareFrame(pin);
  }
}

class StrikeFrame implements Play {

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    // spare frame is a completed frame it should not receive new pins
    return List.empty();
  }

  public int score(List<Play> next) {
    int res = 10;
    if (!next.isEmpty()) {
      Play frame = next.head();
      if (frame instanceof IncompleteFrame)
        res = res + ((IncompleteFrame) frame).pin;
      if (frame instanceof NormalFrame)
        res = res + ((NormalFrame) frame).pin1 + ((NormalFrame) frame).pin2;
      if (frame instanceof SpareFrame)
        res = res + 10;
      if (frame instanceof LastThreeFrame)
        res = res + ((LastThreeFrame) frame).pin1 + ((LastThreeFrame) frame).pin2;
      if (frame instanceof StrikeFrame) {
        res = res + 10;
        if (!next.tail().isEmpty()) {
          Play secondFrame = next.tail().head();
          if (secondFrame instanceof IncompleteFrame)
            res = res + ((IncompleteFrame) secondFrame).pin;
          if (secondFrame instanceof NormalFrame)
            res = res + ((NormalFrame) secondFrame).pin1;
          if (secondFrame instanceof SpareFrame)
            res = res + ((SpareFrame) secondFrame).pin;
          if (secondFrame instanceof StrikeFrame)
            res = res + 10;
          if (secondFrame instanceof LastThreeFrame)
            res = res + ((LastThreeFrame) secondFrame).pin1;
        }
      }
    }
    return res;
  }

  public String status() {
    return "X";
  }

  static Play spare() {
    return new StrikeFrame();
  }
}

/**
 * An empty last frame.
 */
class LastEmptyFrame extends EmptyFrame {

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    if (pin < 10)
      return List.of(LastToTwoFrame.pin(pin));
    else if (pin == 10)
      return List.of(LastToTwoThreeFrame.strike());
    else // shouldn't happen
      return List.empty();
  }

  static Play last() {
    return new LastEmptyFrame();
  }
}

/**
 * A last frame where the first pin is not a strike.
 */
class LastToTwoFrame extends IncompleteFrame {

  LastToTwoFrame(int pin) {
    super(pin);
  }

  @Override
  public List<Play> pin(List<Play> current, int pin) {
    if (this.pin + pin < 10)
      return List.of(LastTwoFrame.done(this.pin, pin));
    if (this.pin + pin == 10)
      return List.of(LastToThreeFrame.done(this.pin, pin));
    else if (pin == 10)
      return List.of(LastToTwoThreeFrame.strike());
    else // shouldn't happen
      return List.empty();
  }

  static Play pin(int pin) {
    return new LastToTwoFrame(pin);
  }
}

/**
 * A last frame that ended in two shots
 *
 */
class LastTwoFrame extends NormalFrame {

  LastTwoFrame(int pin1, int pin2) {
    super(pin1, pin2);
  }

  static Play done(int pin1, int pin2) {
    return new LastTwoFrame(pin1, pin2);
  }

}

class LastToThreeFrame extends NormalFrame {

  LastToThreeFrame(int pin1, int pin2) {
    super(pin1, pin2);
  }

  public List<Play> pin(List<Play> current, int pin) {
    return List.of(LastThreeFrame.done(pin1, pin2, pin));
  }

  static Play done(int pin1, int pin2) {
    return new LastToThreeFrame(pin1, pin2);
  }

  static Play strike(int pin1) {
    return new LastToThreeFrame(pin1, 10);
  }
}

/**
 * A last frame with a first strike
 *
 */
class LastToTwoThreeFrame extends IncompleteFrame {

  LastToTwoThreeFrame(int pin) {
    super(pin);
  }

  public List<Play> pin(List<Play> current, int pin) {
    return List.of(LastToThreeFrame.done(10, pin));
  }

  static Play strike() {
    return new LastToTwoThreeFrame(10);
  }

}

class LastThreeFrame implements Play {

  int pin1;
  int pin2;
  int pin3;

  public LastThreeFrame(int pin1, int pin2, int pin3) {
    this.pin1 = pin1;
    this.pin2 = pin2;
    this.pin3 = pin3;
  }

  public List<Play> pin(List<Play> current, int pin) {
    return List.empty();
  }

  public String status() {
    String c1 = pinStatus(pin1);
    String c2 = pinStatus(pin2);
    String c3 = pinStatus(pin3);

    if (pin1 < 10 && pin1 + pin2 == 10)
      c2 = "/";
    return c1 + c2 + c3;
  }

  public int score(List<Play> rest) {
    return pin1 + pin2 + pin3;
  }

  String pinStatus(int pin) {
    if (pin == 0)
      return "-";
    else if (pin == 10)
      return "X";
    else
      return "" + pin;
  }

  static Play done(int pin1, int pin2, int pin3) {
    return new LastThreeFrame(pin1, pin2, pin3);
  }
}

/**
 * Una classe che accumula il risultato di una partita di bowling ricevendo il valore di un tiro alla volta.
 */
public class BowlingScorer {

  List<Play> game;

  public BowlingScorer() {
    this.game = List.of(EmptyFrame.empty());
  }

  public BowlingScorer(List<Play> status) {
    this.game = status;
  }

  public BowlingScorer pin(int pin) {
    List<Play> newHead = game.head().pin(game, pin);
    return new BowlingScorer(newHead.reverse().appendAll(game.tail()));
  }

  public String status() {
    return game.reverse().map(p -> p.status()).mkString();
  }

  public int score() {
    return score(game.reverse(), 0);
  }

  private int score(List<Play> rest, int sum) {
    if (rest.isEmpty())
      return sum;
    else
      return score(rest.tail(), sum + rest.head().score(rest.tail()));
  }

}
