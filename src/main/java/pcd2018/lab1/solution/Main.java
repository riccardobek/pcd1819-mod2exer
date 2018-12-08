package pcd2018.lab1.solution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import pcd2018.lab1.bowling.GameRecord;
import pcd2018.lab1.data.DataRecord;
import pcd2018.lab1.data.FinalRecord;

public class Main {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    // Preparazione dei componenti

    BlockingQueue<GameRecord> inputQueue = new LinkedBlockingQueue<GameRecord>(1024 * 16);
    BlockingQueue<DataRecord> topPlayers = new LinkedBlockingQueue<DataRecord>(2048);
    BlockingQueue<DataRecord> topVenues = new LinkedBlockingQueue<DataRecord>(2048);

    // Lettura dei file di input
    Stream<ReadScore> inputs = Stream
        .of("source-a.xz", "source-b.xz", "source-c.xz", "source-d.xz", "source-e.xz", "source-f.xz", "source-g.xz")
        .map((fileName) -> {
          ReadScore res;
          try {
            res = new ReadScore("src/main/resources/" + fileName, inputQueue);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
            res = null;
          } catch (IOException e) {
            e.printStackTrace();
            res = null;
          }
          return res;
        });

    // Trasformazione dei record letti
    TransformScore transformer = new TransformScore(inputQueue, new GameRecordToData(GameRecord::player), topPlayers,
        new GameRecordToData(GameRecord::venue), topVenues);
    // Sommatoria secondo giocatori e piste
    Summarizer topPlayersSum = new Summarizer(topPlayers);
    Summarizer topVenuesSum = new Summarizer(topVenues);

    // Calcolo:
    // Top 10 giocatori (media strike)
    // Top 10 piste (media punti per partita)

    ExecutorService consumers = Executors.newFixedThreadPool(3);
    consumers.execute(topPlayersSum);
    consumers.execute(topVenuesSum);
    Future<?> transformerFuture = consumers.submit(transformer);

    ExecutorService producers = Executors.newFixedThreadPool(2);
    inputs.forEach((input) -> {
      producers.execute(input);
    });

    System.out.println("Readers submitted.");
    producers.shutdown();
    while (!producers.awaitTermination(1, TimeUnit.SECONDS))
      System.out.println("Status: " + transformer.counted() + " " + inputQueue.size() + " " + topPlayers.size() + " "
          + topVenues.size());

    System.out.println("Producers done.");
    transformer.done();
    transformerFuture.get();
    topPlayersSum.done();
    topVenuesSum.done();
    consumers.shutdown();
    while (!consumers.awaitTermination(1, TimeUnit.SECONDS))
      System.out.println("Consumer finishing..." + transformer.counted() + " " + inputQueue.size() + " "
          + topPlayers.size() + " " + topVenues.size());
    System.out.println("Consumers done.");

    System.out.println("Top players counting.");
    TreeSet<FinalRecord> strikes = entrydump(topPlayersSum.results().entrySet(), FinalRecord.AVGSTRIKES);
    System.out.println("Top venues counting.");
    TreeSet<FinalRecord> venues = entrydump(topVenuesSum.results().entrySet(), FinalRecord.AVGSCORE);
    entryprint(strikes, 10);
    System.out.println();
    entryprint(venues, 10);

    System.out.println("Games by players: " + entrySum(topPlayersSum.results().entrySet()));
    System.out.println("Games by venues: " + entrySum(topVenuesSum.results().entrySet()));

  }

  private static int entrySum(Set<Entry<String, int[]>> set) {
    int res = 0;
    for (Entry<String, int[]> e : set)
      res += e.getValue()[0];
    return res;
  }

  static TreeSet<FinalRecord> entrydump(Set<Entry<String, int[]>> source, Comparator<FinalRecord> comparator) {
    TreeSet<FinalRecord> res = new TreeSet<FinalRecord>(comparator);
    for (Entry<String, int[]> e : source) {
      int[] val = e.getValue();
      res.add(new FinalRecord(e.getKey(), val[0], val[1], val[2], val[3], val[4]));
    }
    return res;
  }

  static void entryprint(TreeSet<FinalRecord> source, int amount) {
    Iterator<FinalRecord> iterator = source.iterator();
    for (int i = 0; i < amount && iterator.hasNext(); i++) {
      FinalRecord next = iterator.next();
      System.out.println(String.format("%d. %s %6d %6d %5d %5d %5d %7.5f %7.5f %7.5f %7.5f", i + 1, next.key,
          next.totgames, next.totscore, next.totstrikes, next.totspares, next.totgutters, next.avgscore,
          next.avgstrikes, next.avgspares, next.avggutters));
    }
  }

}
