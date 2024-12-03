import scala.io.Source

class Day2(source: Source) {
  // Parse the input into a Seq of Seq of integers
  private val reports = source.getLines.map(raw"\d+".r.findAllIn(_).map(_.toInt).toSeq).toSeq

  private def isSafe(report: Seq[Int]): Boolean = {
    val isMonotonic = {
      val sorted = report.sorted
      report == sorted || report == sorted.reverse
    }
    val areDifferencesOk = report.sliding(2).forall { pair =>
      val diff = (pair.head - pair.last).abs
      1 <= diff && diff <= 3
    }

    isMonotonic && areDifferencesOk
  }

  val ex1: Int = reports.count(isSafe)

  // Brute-force, check if each line is safe by removing each character one by one
  val ex2: Int = reports.count { report =>
    isSafe(report) || report.indices.exists(i => isSafe(report.patch(i, Nil, 1)))
  }
}
