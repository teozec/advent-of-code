import scala.io.Source

@main
def main(): Unit = {
  val day1 = Day1(Source.fromResource("inputs/Day 1.txt"))
  println(s"Ex 1-1: ${day1.ex1}")
  println(s"Ex 1-2: ${day1.ex2}")

  val day2 = Day2(Source.fromResource("inputs/Day 2.txt"))
  println(s"Ex 2-1: ${day2.ex1}")
  println(s"Ex 2-2: ${day2.ex2}")

  val day3 = Day3(Source.fromResource("inputs/Day 3.txt"))
  println(s"Ex 3-1: ${day3.ex1}")
  println(s"Ex 3-2: ${day3.ex2}")

  val day5 = Day5(Source.fromResource("inputs/Day 5.txt"))
  println(s"Ex 5-1: ${day5.ex1}")
  println(s"Ex 5-2: ${day5.ex2}")
}
