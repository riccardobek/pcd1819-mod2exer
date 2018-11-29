package pcd2018.lab1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

import org.tukaani.xz.XZInputStream;

import pcd2018.lab1.bowling.GameRecord;

/**
 * Legge un file di risultati, producendo un record per riga.
 */
public class ScoreReader implements Supplier<GameRecord> {

  private BufferedReader reader;

  public ScoreReader(String fileName) throws FileNotFoundException, IOException {
    reader = new BufferedReader(new InputStreamReader(new XZInputStream(new FileInputStream(fileName))));
  }

  @Override
  public GameRecord get() {
    // FIXME
    return null;
  }

  /**
   * Esamina una riga
   * 
   * @param line Riga letta dal file
   * @return Record ottenuto leggendo la riga
   */
  static GameRecord parse(String line) {
    // FIXME
    return null;
  }

}
