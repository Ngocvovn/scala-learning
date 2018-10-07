object structuralInduction {

  abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
  }

  object Empty extends IntSet {
    override def contains(x: Int): Boolean = false

    override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  }

  case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    override def contains(x: Int): Boolean = {
      if (x < elem) {
        left contains x
      } else if (x > elem) {
        right contains x
      }
      else true
    }

    override def incl(x: Int): IntSet =
      if (x < elem) NonEmpty(elem, left incl x, right)
      else if (x > elem) NonEmpty(elem, left, right incl x)
      else this
  }
}