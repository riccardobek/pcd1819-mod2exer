package pcd2018.safe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A test that will succeed. Compare with AdderTest.
 */
class AtomicAdderTest {

  @Test
  void test() throws InterruptedException {
    AtomicAdder adder = new AtomicAdder();
    adder.add();
    Thread.sleep(1000);
    assertEquals(300000, adder.target.get());
  }

}
