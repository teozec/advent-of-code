import scala.annotation.tailrec
import scala.io.Source

class Day7(source: Source) {
  private class Equation(input: String) {
    private val globalPattern = raw"(\d+):(.*)".r
    private val intPattern = raw"(\d+)".r

    val (target: Long, ints: Seq[Long]) = input match {
      case globalPattern(target, ints) =>
        (target.toLong, intPattern.findAllMatchIn(ints).map(_.group(1).toLong).toSeq)
    }

    private val results: Seq[Long] = {
        ints.drop(1).foldRight(Seq(target)) { (int, targets) =>
          targets.flatMap { t =>
            if (t % int == 0)
              Seq(t - int, t / int)
            else
              Seq(t - int)
          }
        }
    }

    val isObtainable: Boolean = results.contains(ints.head)
  }

  private val equations = source.getLines.map(Equation(_))

  val ex1: Long = equations.filter(_.isObtainable).map(_.target).sum
}
