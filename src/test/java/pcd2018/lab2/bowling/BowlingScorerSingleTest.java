package pcd2018.lab2.bowling;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Single game runner to inspect the status of the BowlingScorer after each call.
 */
public class BowlingScorerSingleTest {

  @Test
  @Tag("Bowling")
  public void test() {
    BowlingScorer scorer = new BowlingScorer();
    // "9-X8-9-9-9-9-9-9-X23",
    for (int pin : new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 }) {
      scorer = scorer.pin(pin);
      System.out.println(pin + " " + scorer.score() + " " + scorer.status() + " " + scorer.game.head().toString());
    }
  }

}
