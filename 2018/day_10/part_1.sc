import scala.io.Source

case class Velocity(vX: Int, vY: Int)

case class Point(x: Int, y: Int)

var pointsAndVelocities: Seq[(Point, Velocity)] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_10/input").getLines.toSeq.map { l =>
  val a = l.split("<")
  val ps = a(1).split(">")
  val vs = a(2).split(">")
  val x = ps(0).split(", ")(0).trim.toInt
  val y = ps(0).split(", ")(1).trim.toInt
  val vX = vs(0).split(", ")(0).trim.toInt
  val vY = vs(0).split(", ")(1).trim.toInt
  (Point(x, y), Velocity(vX, vY))
}

case class GridSize(xMin: Int, yMin: Int, xMax: Int, yMax: Int)

def size(ps: Seq[Point]): GridSize =
  GridSize(ps.minBy(p => p.x).x, ps.minBy(p => p.y).y, ps.maxBy(p => p.x).x, ps.maxBy(p => p.y).y)
def area(g: GridSize): Long =
  (g.xMax.toLong - g.xMin.toLong) * (g.yMax.toLong - g.yMin.toLong)
def nextPoints(ps: Seq[Point], vs: Seq[Velocity]): Seq[Point] =
  ps.zip(vs).map { case (p, v) => Point(p.x + v.vX, p.y + v.vY) }
def lastPoints(ps: Seq[Point], vs: Seq[Velocity]): Seq[Point] =
  ps.zip(vs).map { case (p, v) => Point(p.x - v.vX, p.y - v.vY) }

var i = 0
var points = pointsAndVelocities.map(i => i._1)
var velocities = pointsAndVelocities.map(i => i._2)
var currentArea = area(size(points))
var delta = 10000000000000000L
while(delta > 0) {
  i = i + 1
  val prevArea = currentArea
  points = nextPoints(points, velocities)
  currentArea = area(size(points))
  delta = prevArea - currentArea
}

points = lastPoints(points, velocities)
val gs = size(points)
for(y <- gs.yMin to gs.yMax) {
  for(x <- gs.xMin to gs.xMax)
    if(points.contains(Point(x, y)))
      print('#')
    else
      print(' ')
  println()
}

println(s"${i - 1}")
