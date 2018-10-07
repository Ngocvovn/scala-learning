object examples {
  case class Book(title: String, authors: List[String])

  val books: List[Book] = List(Book(title = "Structure and Interpretation of Computer Programs",
  authors = List("Abelson, Harald", "Sussman", "Gerald J")),
  Book(title = "Introduction to Functional Programming",
  authors = List("Bird", "Richard", "Wadler", "Phil")),
  Book(title = "Effective Java",
  authors = List("Bloch", "Joshua")),
  Book(title = "Java Puzzlers",
  authors = List("Bloch", "Joshua", "Gafter", "Neal")),
  Book(title = "Programming in Scala",
  authors = List("Odersky", "Martin", "Spoon", "Lex", "Venners", "Bill")))

  val authors = books flatMap (book => book.authors) toSet
  /*def maxIncreaseKeepingSkyline(grid: Array[Array[Int]]): Int = {
    val length: Int = grid.length
    val maxTop: List[(Int, Int)] = {
      for {
        row <- 0 until grid.length
        column <- 0 until grid(row).length
        if gr
      }
    }
  }*/

  trait Generator[+T] {
    self =>
    def generate: T
    def map[S](f: T => S): Generator[S] = new Generator[S] {
      override def generate: S = f(self.generate)
    }

    def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S]  {
      override def generate: S = f(self.generate).generate
    }
  }
  val integers = new Generator[Int] {
    val rand = new java.util.Random

    override def generate: Int = rand.nextInt()
  }

  def choose(low: Int, high: Int): Generator[Int] = {
    for (x <- integers) yield low + x % (high -low)
  }


  choose(1, 4).generate



  val booleans = new Generator[Boolean] {
    override def generate: Boolean = integers.generate > 0
  }

  trait Tree
  case class Inner(left: Tree, right: Tree) extends Tree
  case class Leaf(x: Int) extends Tree

  def leafs: Generator[Leaf] = for {
    x <- integers
  } yield Leaf(x)

  def inners: Generator[Inner] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r)

  def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if(isLeaf) leafs else inners
  } yield tree
}