import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day5Test extends AnyFunSuite {
  test("Day5 Ex1") {
    assert(Day5(Source.fromString("ugknbfddgicrmopn")).ex1 == 1)
    assert(Day5(Source.fromString("aaa")).ex1 == 1)
    assert(Day5(Source.fromString("jchzalrnumimnmhp")).ex1 == 0)
    assert(Day5(Source.fromString("haegwjzuvuyypxyu")).ex1 == 0)
    assert(Day5(Source.fromString("dvszwmarrgswjxmb")).ex1 == 0)
  }

  test("Day5 Ex2") {
    assert(Day5(Source.fromString("qjhvhtzxzqqjkmpb")).ex2 == 1)
    assert(Day5(Source.fromString("xxyxx")).ex2 == 1)
    assert(Day5(Source.fromString("uurcxstgmygtbstg")).ex2 == 0)
    assert(Day5(Source.fromString("ieodomkazucvgmuy")).ex2 == 0)
  }
}
