import org.scalatest.funsuite.AnyFunSuite


class Day3Test extends AnyFunSuite {
  test("Ex 1-1") {
    assert(Day3("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))").ex1 == 161)
  }

  test("Ex 1-2") {
    assert(Day3("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))").ex2 == 48)
  }
}
