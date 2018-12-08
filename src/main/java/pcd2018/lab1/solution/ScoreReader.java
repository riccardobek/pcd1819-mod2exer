package pcd2018.lab1.solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

import org.tukaani.xz.XZInputStream;

import pcd2018.lab1.bowling.GameRecord;

/**
 * Legge un file di risultati, leggendo un record per riga.
 */
public class ScoreReader implements Supplier<GameRecord> {

  private BufferedReader reader;

  public ScoreReader(String fileName) throws FileNotFoundException, IOException {
    reader = new BufferedReader(new InputStreamReader(new XZInputStream(new FileInputStream(fileName))));
  }

  @Override
  public GameRecord get() {
    GameRecord parsed;
    try {
      String line = reader.readLine();
      parsed = line != null ? parse(line) : null;
    } catch (IOException e) {
      e.printStackTrace();
      parsed = null;
    }
    return parsed;
  }

  static GameRecord parse(String line) {
    String[] split = line.split("\\|");
    return new GameRecord(split[0], split[1], Integer.valueOf(split[2]), split[3]);

  }

}
