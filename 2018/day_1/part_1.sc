import scala.io.Source
val input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_1/input").getLines.toSeq.map(_.toInt)

//Solution
input.sum
