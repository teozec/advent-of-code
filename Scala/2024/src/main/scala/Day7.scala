import scala.io.Source

class Day7(source: Source) {
  private class Equation(input: String) {
    private val globalPattern = raw"(\d+):(.*)".r
    private val intPattern = raw"(\d+)".r

    val (target: Long, ints: Seq[Long]) = input match {
      case globalPattern(target, ints) =>
        (target.toLong, intPattern.findAllMatchIn(ints).map(_.group(1).toLong).toSeq)
    }

    private def results(operations: Seq[(Long, Long) => Long]): Seq[Long] = {
      ints.drop(1).foldLeft(Seq(ints.head)) { (acc, int) =>
        acc.flatMap(t => operations.map(_(t, int)))
          .filter(_ <= target).distinct
      }
    }

    val isObtainable1: Boolean = results(Seq(_+_, _*_)).contains(target)
    val isObtainable2: Boolean = results(Seq(_+_, _*_, (x, y) => (x.toString + y).toLong)).contains(target)
  }

  private val equations = source.getLines.map(Equation(_)).toSeq

  val ex1: Long = equations.filter(_.isObtainable1).map(_.target).sum
  val ex2: Long = equations.filter(_.isObtainable2).map(_.target).sum
}
