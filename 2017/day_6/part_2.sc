import scala.io.Source
val input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_6/input").getLines().toSeq.map(_.toInt)
var array: scala.collection.mutable.Seq[Int] = scala.collection.mutable.Seq(input: _*)

var steps: Int = 0
var flag = true
var seen: Seq[scala.collection.mutable.Seq[Int]] = Seq.empty
while(flag){
  if(seen.contains(array)){
    flag = false
  } else {
    seen = seen :+ array.clone()
    val largest = array.max
    val largestBank = array.zipWithIndex.find(_._1 == largest).head
    array(largestBank._2) = 0
    var bankToAddTo = largestBank._2 + 1
    for(_ <- 0 until largest){
      if(bankToAddTo >= array.length){
        bankToAddTo = 0
      }
      array(bankToAddTo) = array(bankToAddTo) + 1
      bankToAddTo = bankToAddTo + 1
    }
    steps = steps + 1
  }
}
steps
println(steps - seen.zipWithIndex.find(s => s._1 == array).head._2)
