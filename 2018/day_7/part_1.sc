import scala.io.Source
case class Rule(from: String, to: String)
var rules: Seq[Rule] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_7/input").getLines.toSeq.map { l =>
  Rule(l.substring(5,6), l.substring(36, 37))
}
var points: Set[String] = (rules.map(_.from) ++ rules.map(_.to)).toSet

//Solution
var sequence: Seq[String] = Seq.empty
while(points.nonEmpty){
  val currentPoint = points.filter(p => rules.forall(r => r.to != p)).toSeq.minBy(s => s)
  sequence = sequence :+ currentPoint
  points = points.filterNot(_ == currentPoint)
  rules = rules.filter(r => r.from != currentPoint)
}
println(sequence.mkString(""))
