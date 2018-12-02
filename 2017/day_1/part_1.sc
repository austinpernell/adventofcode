import scala.io.Source
val input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_1/input").getLines().toSeq.head.map(_.asDigit)

//Solution
var total: Int = 0
for((x, i) <- input.zipWithIndex){
  val p = if(i + 1 == input.length) 0 else i + 1
  if(x == input(p)) total = total + x
}
total
