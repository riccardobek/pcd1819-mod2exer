package pcd2018.lab1.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Run full tests from data files.
 */
public class FileTest {

  @Test
  public void simplePins() throws IOException {
    testFile("step1.test");
  }

  @Test
  public void simpleSpares() throws IOException {
    testFile("step2.test");
  }

  @Test
  public void symbols() throws IOException {
    testFile("step3.test");
  }

  @Test
  public void strikes() throws IOException {
    testFile("step4.test");
  }

  @Test
  public void normalGames() throws IOException {
    testFile("step5.test");
  }

  @Test
  public void finalFrame() throws IOException {
    testFile("step6.test");
  }

  void testFile(String fileName) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/" + fileName));
    String line = reader.readLine();
    while (line != null) {
      if (!line.startsWith("#")) {
        // System.out.println(fileName + " " + line);
        String[] params = line.split("\\s");
        assertEquals(Integer.parseInt(params[1]), new BowlingGame(params[0]).score(), "Source: " + params[0]);
      }
      line = reader.readLine();
    }
    reader.close();
  }
}
