package pcd2018.channels;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

import pcd2018.sockets.Game;

/**
 * Management status of a single game.
 */
class GameAttachment {
  final int id;
  final Game game;
  final AsynchronousServerSocketChannel server;
  final AsynchronousChannelGroup group;
  final AsynchronousSocketChannel players[];
  final ByteBuffer readBuf = ByteBuffer.allocate(4);

  /**
   * 
   * @param id Game id
   * @param game Game status
   * @param server Communication Channel
   */
  GameAttachment(int id, Game game, AsynchronousServerSocketChannel server, AsynchronousChannelGroup group) {
    this.id = id;
    this.game = game;
    this.server = server;
    this.group = group;
    this.players = new AsynchronousSocketChannel[] {};
  }

  private GameAttachment(int id, Game game, AsynchronousServerSocketChannel server, AsynchronousSocketChannel playerO,
    AsynchronousChannelGroup group) {
    this.id = id;
    this.game = game;
    this.server = server;
    this.group = group;
    this.players = new AsynchronousSocketChannel[] { playerO };
  }

  private GameAttachment(int id, Game game, AsynchronousServerSocketChannel server, AsynchronousSocketChannel playerO,
    AsynchronousSocketChannel playerX, AsynchronousChannelGroup group) {
    this.id = id;
    this.game = game;
    this.server = server;
    this.group = group;
    this.players = new AsynchronousSocketChannel[] { playerO, playerX };
  }

  /**
   * Build the game status for player O
   * 
   * @param socket communication channel
   * @return The staus set up for player O
   */
  GameAttachment playerO(AsynchronousSocketChannel socket) {
    return new GameAttachment(id, game, server, socket, group);
  }

  /**
   * Build the game status for player O
   * 
   * @param socket communication channel
   * @return The staus set up for player O
   */
  GameAttachment playerX(AsynchronousSocketChannel socket) {
    return new GameAttachment(id, game, server, players[0], socket, group);
  }
}