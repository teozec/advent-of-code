import scala.io.Source

class Day2(source: Source) {
  private val pattern = raw"(\d+)x(\d+)x(\d+)".r

  // Parse boxes, ordering sides from shortest to longest
  private val boxes = source.getLines.map {
    case pattern(a, b, c) => Seq(a.toInt, b.toInt, c.toInt).sorted match {
      case Seq(a, b, c) => (a, b, c)
    }
  }.toSeq

  val ex1: Int = {
    def area(a: Int, b: Int, c: Int): Int = {
      2 * a * b + 2 * b * c + 2 * a * c
    }

    // a and b are the two shortest sides
    def slack(a: Int, b: Int, c: Int): Int = {
      a * b
    }

    boxes.map(box => area.tupled(box) + slack.tupled(box)).sum
  }

  val ex2: Int = {
    // a and b are the two shortest sides
    def smallestPerimeter(a: Int, b: Int, c: Int): Int = {
      2 * a + 2 * b
    }

    def volume(a: Int, b: Int, c: Int): Int = {
      a * b * c
    }

    boxes.map(box => smallestPerimeter.tupled(box) + volume.tupled(box)).sum
  }
}
