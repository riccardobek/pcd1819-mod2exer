package pcd2018.streams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.tukaani.xz.XZInputStream;

import pcd2018.lab1.bowling.BowlingGame;
import pcd2018.lab1.bowling.GameRecord;
import pcd2018.lab1.data.DataRecord;
import pcd2018.lab1.data.FinalRecord;

public class Main {

  @SuppressWarnings("resource")
  public static void main(String[] args) throws InterruptedException {

    Summarizer topPlayersSum = new Summarizer(null);
    Summarizer topVenuesSum = new Summarizer(null);

    // Lettura dei file di input
    Stream.of("source-a.xz", "source-b.xz", "source-c.xz", "source-d.xz", "source-e.xz", "source-f.xz", "source-g.xz")
        .map((String s) -> {
          try {
            return new BufferedReader(
                new InputStreamReader(new XZInputStream(new FileInputStream("src/main/resources/" + s)))).lines();
          } catch (IOException e) {
            e.printStackTrace();
            return null;
          }
        }).reduce(Stream.<String> empty(), Stream::concat).map(line -> {
          String[] split = line.split("\\|");
          return new GameRecord(split[0], split[1], Integer.valueOf(split[2]), split[3]);
        }).peek((gr) -> {
          BowlingGame game = new BowlingGame(gr.score);
          DataRecord data = new DataRecord(gr.player, game.score(), game.strikes(), game.spares(), game.gutters());
          topPlayersSum.accept(data);
        }).forEach((gr) -> {
          BowlingGame game = new BowlingGame(gr.score);
          DataRecord data = new DataRecord(gr.venue, game.score(), game.strikes(), game.spares(), game.gutters());
          topVenuesSum.accept(data);
        });

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
