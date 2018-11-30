package pcd2018.safe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * A test that will succeed, but from the wrong reasons. See Lesson 3.
 */
public class ListTest {

  @Test
  public void listTest() throws InterruptedException {
    List<String> list = new ArrayList<String>();
    list.add("a");
    list.add("b");
    list.add("c");
    Thread t1 = new Thread(new ListTraverser(list));
    Thread t2 = new Thread(new ListUpdater(list));
    t1.start();
    t2.start();
    Thread.sleep(1000);
    assertEquals(4, list.size());
  }
}
