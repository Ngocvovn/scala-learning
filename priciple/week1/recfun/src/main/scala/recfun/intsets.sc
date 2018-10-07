import recfun.{List}

object intsets {

  def nth[T](n: Int, list: List[T]): T = {

   if (list.isEmpty || n < 0) {
     throw new IndexOutOfBoundsException("Nil")
   } else if (n == 0) {
     list.head
   } else {
     nth(n-1, list.tail)
   }

  }

}

