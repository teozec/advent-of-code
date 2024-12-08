import scala.io.Source

class Day4(source: Source) {
  private val rows = source.getLines.toSeq

  val ex1: Int = {
    // Calculate / diagonals. To get \ diagonals, call with tab with reversed rows.
    def diagonals(tab: Seq[String]): Seq[String] = {
      val maxN = tab.length - 1

      // Main diagonal (0, 0) to (maxN, maxN)
      val mainDiagonal = (0 to maxN).zip(maxN to 0 by -1)

      // Upper diagonals: for each row i excluding the last, get the diagonal from (i, 0) to (0, i)
      val upperDiagonals = (0 until maxN).map(i => (0 to i).zip(i to 0 by -1))
      // Lower diagonals: for each row i excluding the first, get the diagonal from (i, maxN) to (maxN, i)
      val lowerDiagonals = (1 until maxN + 1).map(i => (i to maxN).zip(maxN to i by -1))

      (upperDiagonals ++ lowerDiagonals :+ mainDiagonal).map(
        _.map((row, col) => tab(row).charAt(col)).mkString)
    }

    val columns = rows.indices.map(r => rows.map(_.charAt(r)).mkString)
    val stringsToScan = rows ++ columns ++ diagonals(rows) ++ diagonals(rows.map(_.reverse))

    stringsToScan.map(raw"X(?=MAS)|S(?=AMX)".r.unanchored.findAllMatchIn(_).length).sum
  }

  val ex2: Int = {
    // Take 9x9 squares by sliding over rows by 3, and sliding over their columns by 3
    rows.sliding(3).map { case Seq(row1, row2, row3) =>
      row1.sliding(3).toSeq
        .zip(row2.sliding(3).toSeq)
        .zip(row3.sliding(3).toSeq)
        .map { case ((a, b), c) => a ++ b ++ c } // Flatten the square to a single 9-character string
        .count("M.M.A.S.S|M.S.A.M.S|S.M.A.S.M|S.S.A.M.M".r.matches(_)) // Match any of the 4 possible X-MAS patterns, flattened
    }.sum
  }
}
