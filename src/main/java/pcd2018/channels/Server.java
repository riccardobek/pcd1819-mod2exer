package pcd2018.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pcd2018.sockets.Game;

public class Server {

  public static final int GAME_PORT = 56763;

  public static void main(String[] args) throws IOException, InterruptedException {

    ExecutorService pool = Executors.newFixedThreadPool(4);
    AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(pool);
    AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open(group)
        .bind(new InetSocketAddress("127.0.0.1", Server.GAME_PORT), 16);

    System.out.println("Accepting...");

    pool.submit(() -> {
      serverSocket.accept(new GameAttachment(1, new Game(), serverSocket, group), new AcceptPlayerO());
    });
  }
}
