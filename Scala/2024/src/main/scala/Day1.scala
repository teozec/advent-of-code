import scala.io.Source

class Day1(source: Source) {
  private val pattern = raw"(\d+)\s+(\d+)".r

  // Parse the two columns into two lists of integers
  private val (left, right) = source.getLines.map {
    case pattern(x, y) => (x.toInt, y.toInt)
  }.toSeq.unzip

  val ex1: Int = left.sorted.zip(right.sorted).map((x, y) => (y - x).abs).sum
  val ex2: Int = left.map(x => x * right.count(_ == x)).sum
}
