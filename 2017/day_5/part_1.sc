import scala.io.Source
val input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_5/input").getLines().toSeq.map(_.toInt)
var array: scala.collection.mutable.Seq[Int] = scala.collection.mutable.Seq(input: _*)

//Solution
var position: Int = 0
var steps = 0
var flag = true
while(flag){
  try {
    val jump = array(position)
    array(position) = array(position) + 1
    position = position + jump
    steps = steps + 1
  } catch {
    case _: Exception =>
      println(steps)
      flag = false
  }
}
