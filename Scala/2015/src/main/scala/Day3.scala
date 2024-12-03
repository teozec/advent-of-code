import scala.annotation.targetName
import scala.io.Source

class Day3(source: Source) {
  implicit class TupleAdd(t: (Int, Int)) {
    @targetName("plus")
    def +(p: (Int, Int)): (Int, Int) = (p._1 + t._1, p._2 + t._2)
  }

  private val directions = source.map {
    case '^' => (0, 1)
    case 'v' => (0, -1)
    case '>' => (1, 0)
    case '<' => (-1, 0)
  }.toSeq

  // Build a seq of visited locations folding over the instruction sequence
  private def path(directions: Seq[(Int, Int)]): Seq[(Int, Int)] = {
    directions.foldLeft(Seq((0, 0))) { (visited, current) =>
      visited :+ (visited.last + current)
    }
  }

  val ex1: Int = path(directions).distinct.length

  // Calculate the two paths and then concatenate them
  val ex2: Int = {
    val (even, odd) = directions.zipWithIndex.partition(_._2 % 2 == 0)
    (path(even.map(_._1)) ++ path(odd.map(_._1))).distinct.length
  }
}
