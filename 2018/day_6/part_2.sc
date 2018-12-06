import scala.io.Source
case class Pos(x: Int, y: Int)
var coords: Seq[Pos] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_6/input").getLines.toSeq.map { l =>
  val coords = l.split(", ")
  Pos(coords(0).toInt, coords(1).toInt)
}

//Solution
def dist(p1: Pos, p2: Pos) = (p1.x - p2.x).abs + (p1.y - p2.y).abs

val minX = coords.minBy(_.x).x
val minY = coords.minBy(_.y).y
val maxX = coords.maxBy(_.x).x
val maxY = coords.maxBy(_.y).y

val grid = {
  for {
    x <- minX to maxX
    y <- minY to maxY
  } yield Pos(x, y)
}

grid.count(p => coords.map(dist(_, p)).sum < 10000)
