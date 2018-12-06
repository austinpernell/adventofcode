import scala.io.Source
case class Pos(x: Int, y: Int)
var coords: Seq[Pos] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_6/input").getLines.toSeq.map { l =>
  val coords = l.split(", ")
  Pos(coords(0).toInt, coords(1).toInt)
}

//Solution
def dist(p1: Pos, p2: Pos): Int = (p1.x - p2.x).abs + (p1.y - p2.y).abs

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

def closestCoord(pos: Pos) = {
  val closest = coords.map(coord => coord -> dist(coord,  pos)).sortBy(_._2)
  if(closest.head._2 != closest.tail.head._2)
    Some(closest.head._1)
  else
    None
}

val gridWithClosest = (for {
  pos <- grid
  coord <- closestCoord(pos)
} yield pos -> coord).toMap

val onlyFinite = gridWithClosest.filterKeys(pos => pos.x != minX && pos.x != maxX && pos.y != minY && pos.y != maxY)
val finiteCoordSizes = onlyFinite.groupBy(_._2).mapValues(_.size)
finiteCoordSizes.values.max
