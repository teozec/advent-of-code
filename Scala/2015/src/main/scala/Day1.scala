import scala.annotation.tailrec
import scala.io.Source

class Day1(source: Source) {
  private val instructions = source.toSeq
  val ex1: Int = instructions.count(_ == '(') - instructions.count(_ == ')')

  // Loop over the instructions until the floor is -1
  val ex2: Int = {
    @tailrec
    def calc(instructions: Seq[Char], index: Int, currentFloor: Int): Option[Int] = {
      if (currentFloor == -1) {
        Some(index)
      } else instructions match {
        case '(' +: rem => calc(rem, index + 1, currentFloor + 1)
        case ')' +: rem => calc(rem, index + 1, currentFloor - 1)
        case Nil => None
      }
    }

    calc(instructions, 0, 0).getOrElse(-1)
  }
}
