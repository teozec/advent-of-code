import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day2Test extends AnyFunSuite {
  test("Day2 Ex1") {
    assert(Day2(Source.fromString("2x3x4")).ex1 == 58)
    assert(Day2(Source.fromString("4x2x3")).ex1 == 58)
    assert(Day2(Source.fromString("1x1x10")).ex1 == 43)
    assert(Day2(Source.fromString("10x1x1")).ex1 == 43)
  }

  test("Day2 Ex2") {
    assert(Day2(Source.fromString("2x3x4")).ex2 == 34)
    assert(Day2(Source.fromString("4x2x3")).ex2 == 34)
    assert(Day2(Source.fromString("1x1x10")).ex2 == 14)
    assert(Day2(Source.fromString("10x1x1")).ex2 == 14)
  }
}

