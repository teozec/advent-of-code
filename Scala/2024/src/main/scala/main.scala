import scala.io.Source

@main
def main(): Unit = {
  val day1 = Day1(Source.fromResource("inputs/Day 1.txt"))
  println(s"Ex 1-1: ${day1.ex1}")
  println(s"Ex 1-2: ${day1.ex2}")

  val day2 = Day2(Source.fromResource("inputs/Day 2.txt"))
  println(s"Ex 2-1: ${day2.ex1}")
  println(s"Ex 2-2: ${day2.ex2}")

  val day3 = Day3(Source.fromResource("inputs/Day 3.txt").mkString)
  println(s"Ex 3-1: ${day3.ex1}")
  println(s"Ex 3-2: ${day3.ex2}")

  val day4 = Day4(Source.fromResource("inputs/Day 4.txt"))
  println(s"Ex 4-1: ${day4.ex1}")
  println(s"Ex 4-1: ${day4.ex2}")

  val day5 = Day5(Source.fromResource("inputs/Day 5.txt"))
  println(s"Ex 5-1: ${day5.ex1}")
  println(s"Ex 5-2: ${day5.ex2}")

  val day6 = Day6(Source.fromResource("inputs/Day 6.txt"))
  println(s"Ex 6-1: ${day6.ex1}")
  println(s"Ex 6-2: ${day6.ex2}")

  val day7 = Day7(Source.fromResource("inputs/Day 7.txt"))
  println(s"Ex 7-1: ${day7.ex1}")
  println(s"Ex 7-2: ${day7.ex2}")
}
