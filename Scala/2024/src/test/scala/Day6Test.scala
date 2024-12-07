import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day6Test extends AnyFunSuite {
  private val day6 = Day6(Source.fromString(
    """....#.....
      |.........#
      |..........
      |..#.......
      |.......#..
      |..........
      |.#..^.....
      |........#.
      |#.........
      |......#...""".stripMargin))

  test("Ex 6-1") {
    assert(day6.ex1 == 41)
  }

  test("Ex 6-2") {
    assert(day6.ex2 == 6)
  }
}
