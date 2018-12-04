import scala.io.Source
val input: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_4/input").getLines().toSeq

//Solution
input.count{line =>
  val words = line.split(" ").map(_.groupBy(identity).mapValues(_.length))
  words.toSet.size == words.length
}
