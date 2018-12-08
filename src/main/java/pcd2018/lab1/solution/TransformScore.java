package pcd2018.lab1.solution;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

import pcd2018.lab1.bowling.GameRecord;
import pcd2018.lab1.data.DataRecord;

class TransformScore implements Runnable, Consumer<GameRecord> {

  private BlockingQueue<GameRecord> source;
  private GameRecordToData transformer1;
  private BlockingQueue<DataRecord> dest1;
  private GameRecordToData transformer2;
  private BlockingQueue<DataRecord> dest2;
  private int counted;
  private boolean done = false;

  TransformScore(BlockingQueue<GameRecord> source, GameRecordToData transformer1, BlockingQueue<DataRecord> dest1,
    GameRecordToData transformer2, BlockingQueue<DataRecord> dest2) {
    this.source = source;
    this.transformer1 = transformer1;
    this.dest1 = dest1;
    this.transformer2 = transformer2;
    this.dest2 = dest2;
    this.counted = 0;
  }

  @Override
  public void run() {
    while (!done) {
      try {
        GameRecord record = source.take();
        accept(record);
      } catch (InterruptedException e) {
        System.out.println("TransformScore interrupted with " + source.size() + " input remaining.");
      }
    }
    System.out.println("TransformScore draining: " + source.size() + " results.");
    source.forEach(this);
    System.out.println("TransformScore done.");
  }

  @Override
  public void accept(GameRecord record) {
    try {
      dest1.put(transformer1.apply(record));
      dest2.put(transformer2.apply(record));
      counted++;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public int counted() {
    return counted;
  }

  public void done() {
    this.done = true;
  }
}