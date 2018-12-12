package pcd2018.sockets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameResultTest {

  @Test
  public void testBlank() {
    GameResult gameResult = new GameResult(Game.PLAYER_O, true, false, new int[] { 0, 0 });
    String repr = gameResult.toString();
    System.out.println(repr);
    assertEquals("PLAYER_O true false\n_ _ _\n_ _ _\n_ _ _\n0 1 2 3 4 5 6 7 8", repr, "Inizio partita");

  }

  @Test
  public void testOne() {
    GameResult gameResult = new GameResult(Game.PLAYER_X, true, false, new int[] { 0x4, 0 });
    String repr = gameResult.toString();
    System.out.println(repr);
    assertEquals("PLAYER_X true false\n_ _ O\n_ _ _\n_ _ _\n0 1 3 4 5 6 7 8", repr, "Inizio partita");
  }

  @Test
  public void testFinal() {
    GameResult gameResult = new GameResult(Game.PLAYER_X, true, true, new int[] { 0xc5, 0x138 });
    String repr = gameResult.toString();
    System.out.println(repr);
    assertEquals("PLAYER_X true true\nO _ O\nX X X\nO O X\n1", repr, "Fine partita");
  }

}
