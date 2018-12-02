import scala.io.Source
val input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_1/input").getLines.toSeq.map(_.toInt)

//Solution
def work(input: Seq[Int], position: Int = 0, total: Int = 0, seen: Set[Int] = Set.empty): Int = {
  val p = if(position == input.size) 0 else position
  if(seen.contains(total))
    total
  else
    work(input, p + 1, total + input(p), seen + total)
}
work(input)
