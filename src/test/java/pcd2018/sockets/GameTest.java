package pcd2018.sockets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pcd2018.sockets.Game.PLAYER_O;

import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  public void testGame() {
    Game game = new Game();
    assertInitial(game.status(), Game.PLAYER_O, 0, 1, 2, 3, 4, 5, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_O, 4), 0, 1, 2, 3, 5, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_X, 3), 0, 1, 2, 5, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_O, 8), 0, 1, 2, 5, 6, 7);
    assertValid(game.move(Game.PLAYER_X, 0), 1, 2, 5, 6, 7);
    assertValid(game.move(Game.PLAYER_O, 6), 1, 2, 5, 7);
    assertValid(game.move(Game.PLAYER_X, 2), 1, 5, 7);
    assertWon(game.move(Game.PLAYER_O, 7), PLAYER_O, 1, 5);
  }

  @Test
  public void testTie() {
    Game game = new Game();
    assertInitial(game.status(), Game.PLAYER_O, 0, 1, 2, 3, 4, 5, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_O, 5), 0, 1, 2, 3, 4, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_X, 2), 0, 1, 3, 4, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_O, 4), 0, 1, 3, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_X, 3), 0, 1, 6, 7, 8);
    assertValid(game.move(Game.PLAYER_O, 8), 0, 1, 6, 7);
    assertValid(game.move(Game.PLAYER_X, 0), 1, 6, 7);
    assertValid(game.move(Game.PLAYER_O, 6), 1, 7);
    assertValid(game.move(Game.PLAYER_X, 7), 1);
    assertTied(game.move(Game.PLAYER_O, 1));
  }

  private void assertTied(GameResult move) {
    System.out.println(move);
    assertFalse(move.valid, "is not valid");
    assertTrue(move.end, "is done");
  }

  private void assertInitial(GameResult status, int player, int... avails) {
    System.out.println(status);
    assertTrue(status.valid, "is valid");
    assertFalse(status.end, "is not done");
    assertEquals(PLAYER_O, status.next, "player O starts");
    assertAvails(status.board, avails);

  }

  private void assertWon(GameResult move, int winner, int... avails) {
    System.out.println(move);
    assertTrue(move.valid, "is valid");
    assertTrue(move.end, "is done");
    assertEquals(winner, move.next, "winner");
    assertAvails(move.board, avails);
  }

  void assertAvails(String board, int... avails) {
    String[] split = board.substring(board.lastIndexOf('\n') + 1).split("\\s");
    System.out.println(">>> " + board.substring(board.lastIndexOf('\n') + 1));
    assertEquals(avails.length, split.length, "avails: " + avails.length);
    for (int i = 0; i < split.length; i++)
      assertEquals(avails[i], Integer.parseInt(split[i]), "i " + avails[i] + " " + split[i]);
  }

  private void assertValid(GameResult move, int... avails) {
    System.out.println(move);
    assertTrue(move.valid, "is valid");
    assertFalse(move.end, "is ended");
    assertAvails(move.board, avails);
  }

  @Test
  public void testIsFree() {
    int[] board = new int[] { 0x40, 0x3 };
    assertFalse(Game.isFree(0, board));
    assertFalse(Game.isFree(1, board));
    assertTrue(Game.isFree(2, board));
    assertTrue(Game.isFree(3, board));
    assertTrue(Game.isFree(4, board));
    assertTrue(Game.isFree(5, board));
    assertFalse(Game.isFree(6, board));
    assertTrue(Game.isFree(7, board));
    assertTrue(Game.isFree(8, board));

  }
}
