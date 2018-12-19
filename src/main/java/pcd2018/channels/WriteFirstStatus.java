package pcd2018.channels;

import java.nio.channels.AsynchronousSocketChannel;

import pcd2018.sockets.Game;
import pcd2018.sockets.GameResult;

/**
 * Step: Write the first game status (no need to check for completed game). Set up the acceptance of new games, if the
 * limit has not been reached yet.
 * 
 * Next: Read player's move
 */
class WriteFirstStatus extends Step<AsynchronousSocketChannel> {
  @Override
  public void completed(AsynchronousSocketChannel result, GameAttachment attachment) {

    System.out.println(Thread.currentThread().getName() + " : game " + attachment.id + " connected for player X");
    attachment = attachment.playerX(result);

    GameResult status = attachment.game.status();
    AsynchronousSocketChannel socket = attachment.players[status.next];
    byte[] bytes = (status.toString() + "\n").getBytes();
    System.out.println(Thread.currentThread().getName() + " : game " + attachment.id + " started");
    socket.write(wrap(bytes), attachment, new ReadPlayer());

    // more games?
    if (attachment.id <= 5) {
      attachment.server.accept(new GameAttachment(attachment.id + 1, new Game(), attachment.server, attachment.group),
          new AcceptPlayerO());
    } else {
      attachment.group.shutdown();
    }
  }

}