package pcd2018.lab1.solution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import pcd2018.lab1.bowling.GameRecord;

class ReadScore implements Runnable {

  private BlockingQueue<GameRecord> dest;
  private ScoreReader source;
  private String sourceFile;

  ReadScore(String sourceFile, BlockingQueue<GameRecord> dest) throws FileNotFoundException, IOException {
    this.sourceFile = sourceFile;
    this.source = new ScoreReader(sourceFile);
    this.dest = dest;
  }

  @Override
  public void run() {
    GameRecord record = source.get();
    while (record != null)
      try {
        dest.put(record);
        record = source.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    System.out.println("Done reading " + sourceFile);
  }

}