import scala.io.Source

class Day5(source: Source) {
  private val strings = source.getLines.toSeq

  val ex1: Int = strings.count { string =>
    val containsThreeVowels = string.count("aeiou".contains(_)) >= 3
    val letterPairs = string.sliding(2).toSeq
    val arePairsOk = letterPairs.exists(_.distinct.length == 1) && !letterPairs.exists(Seq("ab", "cd", "pq", "xy").contains(_))
    containsThreeVowels && arePairsOk
  }

  // TODO: fix (not working)
  val ex2: Int = strings.count { string =>
    val containsRepeatedTwoLetter = string.sliding(2).zipWithIndex.toSeq
      .groupMap(_._1)(_._2).values // Group by string pattern, keep only the indexes
      .filter(_.length > 1)
      .exists(_.sliding(2).exists(pair => pair.last > pair.head + 1))


    val containsRepeatedTwoLetterNew = string.sliding(2).zipWithIndex.toSeq
      .groupMap(_._1)(_._2).values
      .map(_.foldLeft((false, -1)) { (acc, cur) =>
        val (result, last) = acc
        (result, last) match {
          case (_, -1) => (false, cur)
          case (true, _) => (true, cur)
          case (false, prev) => (cur > prev + 1, cur)
        }
      }._1)
      .exists(identity)

    val containsThreeLetterPattern = string.sliding(3).exists(s => s.head == s.last)
    containsRepeatedTwoLetterNew && containsThreeLetterPattern
  }
}
