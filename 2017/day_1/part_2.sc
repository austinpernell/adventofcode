import scala.io.Source
val input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_1/input").getLines().toSeq.head.map(_.asDigit)

//Solution
var total: Int = 0
val halfAround: Int = input.length / 2
for((x, i) <- input.zipWithIndex){
  val p = if(i + halfAround >= input.length) i - halfAround else i + halfAround
  if(x == input(p)) total = total + x
}
total
