object stream {
  class Pouring(capacity: Vector[Int]) {
    type State = Vector[Int]
    val initialState = capacity map (x => 0)

    trait Move {
      def change(state: State): State
    }

    case class Empty(glass: Int) extends Move {
      def change(state: State) = state updated (glass, 0)
    }
    case class Fill(glass: Int) extends Move {
      override def change(state: State): State = state updated(glass, capacity(glass))
    }
    case class Pour(from: Int, to: Int) extends Move {
      override def change(state: State): State = {
        val amount = state(from) min (capacity(to) - state(to))
        state updated (from, state(from) - amount) updated (to, state(to) + amount)
      }
    }
    val glasses = 0 until capacity.length
    val moves =
      (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))
    class Path(history: List[Move]) {
      def endState: State = (history foldRight initialState)(_ change _)
      def extend(move: Move) = new Path(move :: history)

      override def toString: String = (history.reverse mkString " ") + "--> " + endState
    }
    val initialPath = new Path(Nil)

    def from(paths: Set[Path]): Stream[Set[Path]] = {
      if (paths.isEmpty) Stream.empty
      else {
        val more = for {
          path <- paths
          next <- moves map path.extend

        } yield  next
        paths #:: from(more)
      }
    }

    val pathSets = from (Set(initialPath))

    def solutions(target: Int): Stream[Path] = for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
  }

  val problem = new Pouring(Vector(4, 9))
  problem.moves
  problem.solutions(6)


}