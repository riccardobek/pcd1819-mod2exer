package pcd2018.channels;

import java.io.IOException;

/**
 * Step: Close a socket
 * 
 * There is no next step.
 */
class CloseSocket extends Step<Integer> {

  private int idx;

  CloseSocket(int idx) {
    this.idx = idx;
  }

  @Override
  public void completed(Integer result, GameAttachment attachment) {
    try {
      attachment.players[idx].close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}