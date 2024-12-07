import scala.annotation.{tailrec, targetName}
import scala.io.Source
import scala.collection.Searching.{Found, InsertionPoint}

class Day6(source: Source) {
  private val map = source.getLines.toArray

  private class Map(map: Array[String], patch: Option[(Int, Int)] = None) {
    implicit class TupleAdd(t: (Int, Int)) {
      @targetName("plus")
      def +(p: (Int, Int)): (Int, Int) = (p._1 + t._1, p._2 + t._2)
    }

    private val nRows: Int = map.length
    private val nCols: Int = map(0).length

    private def isOutside(pos: (Int, Int)): Boolean = pos match {
      case (row, col) =>
        row < 0 || row >= nRows || col < 0 || col >= nCols
    }

    private def atIndex(pos: (Int, Int)): Char = (patch, pos) match {
      //case (Some(`start`), (row, col)) => map(row).charAt(col)
      case (Some(patch), pos) if patch == pos => '#'
      case (_, (row, col)) => map(row).charAt(col)
    }

    private def indexOf(c: Char): (Int, Int) = {
      val row = map.indexWhere(_.contains(c))
      val col = map(row).indexOf(c)
      (row, col)
    }

    private val start = indexOf('^')

    val (isLoop, path) = {
      val N = (-1, 0)
      val S = (1, 0)
      val W = (0, -1)
      val E = (0, 1)

      @tailrec
      def path(pos: (Int, Int), dir: (Int, Int), previous: IndexedSeq[((Int, Int), (Int, Int))]): (Boolean, IndexedSeq[((Int, Int), (Int, Int))]) = {
        def turnRight: (Int, Int) = dir match {
          case N => E
          case E => S
          case S => W
          case W => N
        }

        val nextPos = pos + dir

        previous.search((pos, dir)) match {
          case Found(_) =>
            (true, previous)
          case InsertionPoint(insertionPoint) =>
            val (front, back) = previous.splitAt(insertionPoint)
            val nextPrevious = front ++ IndexedSeq((pos, dir)) ++ back
            if (isOutside(nextPos))
              (false, nextPrevious)
            else if (atIndex(nextPos) == '#') // Obstacle: turn right
              path(pos, turnRight, nextPrevious)
            else // Go straight by one step
              path(pos + dir, dir, nextPrevious)
        }
      }

      val (isLoop, pathWithDirs) = path(start, N, IndexedSeq())
      (isLoop, pathWithDirs.map { case (pos, dir) => pos }.distinct)
    }
  }

  private val path = new Map(map).path

  val ex1: Int = path.length
  val ex2: Int = path.count { patch =>
    new Map(map, Some(patch)).isLoop
  }

}
