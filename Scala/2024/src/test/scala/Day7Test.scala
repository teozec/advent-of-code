import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day7Test extends AnyFunSuite {
  private val day7 = Day7(Source.fromString(
    """190: 10 19
      |3267: 81 40 27
      |83: 17 5
      |156: 15 6
      |7290: 6 8 6 15
      |161011: 16 10 13
      |192: 17 8 14
      |21037: 9 7 18 13
      |292: 11 6 16 20.""".stripMargin))

  test("Ex 7-1") {
    assert(day7.ex1 == 3749)
  }

  test("Ex 7-2") {
    //assert(day7.ex2 == 7)
  }
}
