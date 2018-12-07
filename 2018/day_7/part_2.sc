import scala.io.Source
case class Rule(from: String, to: String)
var rules: Seq[Rule] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_7/input").getLines.toSeq.map { l =>
  Rule(l.substring(5,6), l.substring(36, 37))
}
var points: Set[String] = (rules.map(_.from) ++ rules.map(_.to)).toSet

var time = 0
var workers: scala.collection.mutable.Seq[Int] = scala.collection.mutable.Seq(0, 0, 0, 0, 0)
var work: scala.collection.mutable.Seq[Option[String]] = scala.collection.mutable.Seq(None, None, None, None, None)
while(points.nonEmpty || workers.exists(_ > 0)) {
  var availableToBeWorkedOn = points.filter(p => rules.forall(r => r.to != p)).toSeq
  for(i <- 0 until 5){
    workers(i) = Math.max(workers(i) -1, 0)
    if(workers(i) == 0){
      if(work(i).nonEmpty){
        rules = rules.filter(r => r.from != work(i).get)
        println(rules.size)
      }
      if(availableToBeWorkedOn.nonEmpty){
        val n = availableToBeWorkedOn.head
        availableToBeWorkedOn = availableToBeWorkedOn.tail
        workers(i) = n.toCharArray.head.toInt - 5
        work(i) = Some(n)
        points = points.filterNot(_ == n)
      }
    }
  }
  time += 1
}
time
