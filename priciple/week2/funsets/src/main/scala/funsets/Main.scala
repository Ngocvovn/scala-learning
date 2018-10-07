package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  println(List(1,2,3) ::: List(3, 4, 5))
}
