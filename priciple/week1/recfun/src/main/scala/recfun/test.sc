object test {

  val x = new Rational(1, 0)
  x.numerator
  -x
}

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non zero")
  def numerator = x
  def denominator = y
  def unary_- : Rational = new Rational(-numerator, denominator)
}



