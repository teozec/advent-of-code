import scala.io.Source

class Day5(source: Source) {
  private val strings = source.getLines.toSeq

  val ex1: Int = strings.count { string =>
    val containsThreeVowels = string.count("aeiou".contains(_)) >= 3
    val letterPairs = string.sliding(2).toSeq
    val arePairsOk = letterPairs.exists(_.distinct.length == 1) && !letterPairs.exists(Seq("ab", "cd", "pq", "xy").contains(_))
    containsThreeVowels && arePairsOk
  }

  val ex2: Int = strings.count { string =>
    val containsRepeatedTwoLetter = raw"(..).*\1".r.unanchored.matches(string)
    val containsThreeLetterPattern = raw"(.).\1".r.unanchored.matches(string)

    containsRepeatedTwoLetter && containsThreeLetterPattern
  }
}
