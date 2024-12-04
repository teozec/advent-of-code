import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source


class Day4Test extends AnyFunSuite {
  private val day4 = Day4(Source.fromString(
    """MMMSXXMASM
      |MSAMXMSMSA
      |AMXSXMAAMM
      |MSAMASMSMX
      |XMASAMXAMM
      |XXAMMXXAMA
      |SMSMSASXSS
      |SAXAMASAAA
      |MAMMMXMMMM
      |MXMXAXMASX""".stripMargin))

  test("Ex 4-1") {
    assert(day4.ex1 == 18)
  }

  test("Ex 4-2") {
    assert(day4.ex2 == 9)
  }
}
