import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day3Test extends AnyFunSuite {
  test("Day3 Ex1") {
    assert(Day3(Source.fromString(">")).ex1 == 2)
    assert(Day3(Source.fromString("^>v<")).ex1 == 4)
    assert(Day3(Source.fromString("^v^v^v^v^v")).ex1 == 2)
  }

  test("Day3 Ex2") {
    assert(Day3(Source.fromString("^v")).ex2 == 3)
    assert(Day3(Source.fromString("^>v<")).ex2 == 3)
    assert(Day3(Source.fromString("^v^v^v^v^v")).ex2 == 11)
  }
}
