import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day1Test extends AnyFunSuite {
  test("Day1 Ex1") {
    assert(Day1(Source.fromString("(())")).ex1 == 0)
    assert(Day1(Source.fromString("()()")).ex1 == 0)
    assert(Day1(Source.fromString("(((")).ex1 == 3)
    assert(Day1(Source.fromString("(()(()(")).ex1 == 3)
    assert(Day1(Source.fromString("))(((((")).ex1 == 3)
    assert(Day1(Source.fromString("())")).ex1 == -1)
    assert(Day1(Source.fromString("))(")).ex1 == -1)
    assert(Day1(Source.fromString(")))")).ex1 == -3)
    assert(Day1(Source.fromString(")())())")).ex1 == -3)
  }

  test("Day1 Ex2") {
    assert(Day1(Source.fromString(")")).ex2 == 1)
    assert(Day1(Source.fromString("()())")).ex2 == 5)
  }
}
