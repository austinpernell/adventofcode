import scala.io.Source
val input: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_2/input").getLines.toSeq

//Solution
val res = input.map{ id =>
  val charMap = id.groupBy(identity).mapValues(_.length)
  (charMap.values.toSeq.contains(2), charMap.values.toSeq.contains(3))
}

res.count(_._1) * res.count(_._2)
