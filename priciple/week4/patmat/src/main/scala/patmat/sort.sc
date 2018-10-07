object sort{
  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length /2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n
      merge(msort(fst)(lt), msort(snd)(lt))
    }
  }

  def isPrime(n: Int): Boolean = {
    (2 until (scala.math.sqrt(n).toInt)) forall(x => n % x != 0)
  }
   isPrime(9)

  val n = 7
  (1 until n) map (i =>
    ((1 until i) map (j => (i, j)) filter (pair => isPrime(pair._1 + pair._2))))

  for {i <- 1 until n
       j <- 1 until i
       if isPrime(i + j)
  } yield (i, j)

  def scalarProduct(xs: List[Double], ys: List[Double]): Double = {
    (for ((x, y) <- xs zip ys) yield x*y).sum
  }

}