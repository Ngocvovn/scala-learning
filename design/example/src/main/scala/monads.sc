object monads {
  trait M[T] {
    def flatMap[U](f: T => M[U]): M[U]
  }
  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  val total = someNumbers.foldLeft(0)(_ + _)

}
