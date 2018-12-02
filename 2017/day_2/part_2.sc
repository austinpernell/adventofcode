import scala.io.Source
val input: Seq[Seq[Int]] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_2/input").getLines.toSeq.map(_.split(" ").map(_.toInt).toSeq)

//Solution
input.map{ line =>
  var num = 0
  for(outer <- line) {
    for(inner <- line){
      if(outer != inner && outer % inner == 0){
        num = outer / inner
      }
    }
  }
  num
}.sum
