package recfun
import scala.Null

trait List[+T] {

  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend [U >: T] (elem: U) : List[U] = new Cons(elem ,this)

}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
}




