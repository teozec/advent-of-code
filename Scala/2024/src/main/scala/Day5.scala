import scala.io.Source

class Day5(source: Source) {
  private val lines = source.getLines.toSeq
  private val lists = lines.filter(_.contains(',')).map(_.split(',').map(_.toInt))

  private object Order extends Ordering[Int] {
    private val order = {
      val pattern = raw"(\d+)\|(\d+)".r
      lines.filter(_.contains('|')).map { case pattern(x, y) => (x.toInt, y.toInt) }
    }

    def compare(x: Int, y: Int): Int = {
      if (order.contains((x, y)))
        -1 // x must come before y
      else if (order.contains((y, x)))
        1 // y must come before x
      else throw new Exception("Ordering is not total")
    }
  }

  private def isSorted(l: Array[Int]): Boolean = l.sorted(Order).sameElements(l)
  private def middlePoint(l: Array[Int]): Int = l.drop(l.length / 2).head

  val ex1: Int = lists.filter(isSorted).map(middlePoint).sum
  val ex2: Int = lists.filterNot(isSorted).map(l => middlePoint(l.sorted(Order))).sum
}
