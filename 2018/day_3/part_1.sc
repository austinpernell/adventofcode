import scala.io.Source
val input: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_3/input").getLines.toSeq

//Solution
var grid: Map[(Int, Int), (String, Int)] = Map.empty
for(line <- input){
  val a = line.split("@")
  val b = a(1).split(":")
  val coordString = b(0)
  val sizeString = b(1)

  val claim = a(0).trim.split("#")(1)
  val x = coordString.split(",")(0).trim.toInt
  val y = coordString.split(",")(1).trim.toInt
  val xSize = sizeString.split("x")(0).trim.toInt
  val ySize = sizeString.split("x")(1).trim.toInt

  for(i <- 0 until xSize) {
    for(j <- 0 until ySize){
      val location = (x+i, y+j)
      grid.get(location) match {
        case None => grid = grid + (location ->  (claim -> 1))
        case Some(overlap) => grid = grid + (location -> ("X" -> (overlap._2 + 1)))
      }
    }
  }
}

grid.values.count(overlap => overlap._1 == "X")
