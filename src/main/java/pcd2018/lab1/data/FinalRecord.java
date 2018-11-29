package pcd2018.lab1.data;

import java.util.Comparator;
import java.util.function.Function;

class AvgComparator implements Comparator<FinalRecord> {

  private Function<FinalRecord, Double> extractor;

  AvgComparator(Function<FinalRecord, Double> extractor) {
    this.extractor = extractor;
  }

  @Override
  public int compare(FinalRecord o1, FinalRecord o2) {
    double diff = extractor.apply(o2) - extractor.apply(o1);
    return amplifyDiff(diff);
  }

  static int amplifyDiff(double diff) {
    if (Math.abs(diff) < 0.00000001)
      return 0;
    while (Math.abs(diff) < 1.0)
      diff = diff * 10.0;
    return (int) Math.round(diff);
  }

}

/**
 * Record dei dati sommati finali. Produce anche le medie richieste.
 */
public class FinalRecord {

  public static final Comparator<FinalRecord> AVGSCORE = new AvgComparator((r) -> {
    return r.avgscore;
  });
  public static final Comparator<FinalRecord> AVGSTRIKES = new AvgComparator((r) -> {
    return r.avgstrikes;
  });
  public static final Comparator<FinalRecord> AVGSPARES = new AvgComparator((r) -> {
    return r.avgspares;
  });
  public static final Comparator<FinalRecord> AVGGUTTERS = new AvgComparator((r) -> {
    return r.avggutters;
  });

  public final String key;
  public final int totgames, totscore, totstrikes, totspares, totgutters;
  public final double avgscore, avgstrikes, avgspares, avggutters;

  public FinalRecord(String key, int totgames, int totscore, int totstrikes, int totspares, int totgutters) {
    this.key = key;
    this.totgames = totgames;
    this.totscore = totscore;
    this.totstrikes = totstrikes;
    this.totspares = totspares;
    this.totgutters = totgutters;
    this.avgscore = 1.0 * totscore / totgames;
    this.avgstrikes = 1.0 * totstrikes / totgames;
    this.avgspares = 1.0 * totspares / totgames;
    this.avggutters = 1.0 * totgutters / totgames;
  }

}
