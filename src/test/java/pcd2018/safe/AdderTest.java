package pcd2018.safe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AdderTest {

  /**
   * A test that will fail. See Lesson 15
   * 
   * @throws InterruptedException
   */
  @Test
  @Disabled
  void test() throws InterruptedException {
    Adder adder = new Adder();
    adder.add();
    // Necessary to make the test framework wait for the threads to complete
    Thread.sleep(1000);
    assertEquals(300000, adder.target);
  }

}
