package pcd2018.channels;

import java.nio.channels.AsynchronousSocketChannel;

import pcd2018.sockets.GameResult;

/**
 * Step: Read move from player
 * 
 * Next: Write game status to the other player
 */
class ReadPlayer extends Step<Integer> {

  @Override
  public void completed(Integer result, GameAttachment attachment) {
    GameResult status = attachment.game.status();
    AsynchronousSocketChannel socket = attachment.players[status.next];
    System.out.println(Thread.currentThread().getName() + " : game " + attachment.id + " waiting input from player "
        + (status.next == 0 ? "O" : "X"));
    attachment.readBuf.clear();
    socket.read(attachment.readBuf, attachment, new WriteStatus());
  }

}