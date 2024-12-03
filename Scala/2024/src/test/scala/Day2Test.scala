import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day2Test extends AnyFunSuite {
  private val day2 = new Day2(Source.fromString(
    """7 6 4 2 1
      |1 2 7 8 9
      |9 7 6 2 1
      |1 3 2 4 5
      |8 6 4 4 1
      |1 3 6 7 9""".stripMargin
  ))

  private val mine = new Day2(Source.fromString(
    """3 2 4 5
      |5 4 2 3""".stripMargin
  ))

  test("Ex 2-1") {
    assert(day2.ex1 == 2)
    assert(mine.ex1 == 0)
  }

  test("Ex 2-2") {
    assert(day2.ex2 == 4)
    assert(mine.ex2 == 2)
  }
}
