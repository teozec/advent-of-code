class Day3(input: String) {

  private def calculateResult(input: String): Int = {
    val pattern = raw"mul\((\d{1,3}),(\d{1,3})\)".r
    pattern.findAllMatchIn(input).map(m => m.group(1).toInt * m.group(2).toInt).sum
  }

  val ex1: Int = calculateResult(input)

  // Remove all substrings of the form "don't()...do()" before calculating the result
  val ex2: Int = {
    val pattern = raw"don't\(\).*?(do\(\)|$$)".r
    val correctedInput = pattern.replaceAllIn(input.replaceAll(sys.props("line.separator"), ""), "")
    calculateResult(correctedInput)
  }
}
