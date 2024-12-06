import scala.annotation.{tailrec, targetName}
import scala.io.Source

class Day6(source: Source) {
  implicit class TupleAdd(t: (Int, Int)) {
    @targetName("plus")
    def +(p: (Int, Int)): (Int, Int) = (p._1 + t._1, p._2 + t._2)
  }

  private val N = (-1, 0)
  private val S = (1, 0)
  private val W = (0, -1)
  private val E = (0, 1)

  private val map = source.getLines.toArray

  val ex1: Int = {
    val start = {
      val row = map.indexWhere(_.contains('^'))
      val col = map(row).indexOf('^')
      (row, col)
    }

    @tailrec
    def path(pos: (Int, Int), dir: (Int, Int), previous: Seq[(Int, Int)]): Seq[(Int, Int)] = {

      def turnRight: (Int, Int) = dir match {
        case N => E
        case E => S
        case S => W
        case W => N
      }

      val (row, col) = pos
      val (nextRow, nextCol) = pos + dir

      if (row >= map.length || col >= map(0).length)
        previous
      else if (nextRow >= map.length || nextCol >= map(0).length)
        previous :+ pos
      else if (map(nextRow).charAt(nextCol) == '#')
        path(pos + turnRight, turnRight, previous :+ pos)
      else
        path(pos + dir, dir, previous :+ pos)
    }

    path(start, N, Seq()).distinct.length
  }
}
