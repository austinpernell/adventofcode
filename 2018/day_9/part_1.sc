def rotate[T](circle: scala.collection.mutable.ArrayDeque[T], num: Int): Unit =
  for(_ <- 0 until num.abs) if(num >= 0) circle.prepend(circle.removeLast()) else circle.append(circle.removeHead())

def play(players: Int, end: Int) = {
  val circle = new scala.collection.mutable.ArrayDeque[Int]
  circle.append(0)
  val scores = new Array[Long](players)
  for(i <- 1 to end) {
    if(i % 23 == 0) {
      rotate(circle, 7)
      scores(i % players) += i + circle.removeLast()
      rotate(circle, -1)
    } else {
      rotate(circle, -1)
      circle.append(i)
    }
  }
  scores.max
}

val game1 = play(426, 72058)
val game2 = play(426, 72058 * 100)
