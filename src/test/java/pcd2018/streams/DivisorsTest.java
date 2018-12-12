package pcd2018.streams;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.Test;

import io.vavr.collection.List;

class DivisorsTest {

  @Test
  public void test() {
    Divisors div = new Divisors();
    CandidateNumber div2 = div.apply(2);
    assertIterableEquals(div2.divisors, List.of(2));
    CandidateNumber div6 = div.apply(6);
    assertIterableEquals(div6.divisors, List.of(2, 3));
    CandidateNumber div37 = div.apply(37);
    assertIterableEquals(div37.divisors, List.of());
    CandidateNumber div1024 = div.apply(1024);
    assertIterableEquals(div1024.divisors, List.of(2, 4, 8, 16, 32, 64, 128, 256, 512));
  }

}
