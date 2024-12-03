import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day1Test extends AnyFunSuite {
  private val day1 = new Day1(Source.fromString(
    """3   4
      |4   3
      |2   5
      |1   3
      |3   9
      |3   3""".stripMargin
  ))

  test("Ex 1-1") {
    assert(day1.ex1 == 11)
  }

  test("Ex 1-2") {
    assert(day1.ex2 == 31)
  }
}
