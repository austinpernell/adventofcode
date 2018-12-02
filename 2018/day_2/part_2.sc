import scala.io.Source
val input: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_2/input").getLines.toSeq

//Solution
var flag = true
for(outer <- input){
  for(inner <- input){
    if(outer != inner && flag){
      var mismatch: Int = 0
      for((_, i) <- outer.zipWithIndex){
        if(outer(i) != inner(i)){
          mismatch = mismatch + 1
        }
      }
      if(mismatch == 1){
        println(outer)
        println(inner)
        flag = false
      }
    }
  }
}
