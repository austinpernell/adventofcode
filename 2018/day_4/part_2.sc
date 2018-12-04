import scala.io.Source
val input: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_4/input").getLines.toSeq

//Solution
var grid: Map[String, Seq[(Int, Int)]] = Map.empty
val sorted = input.sortWith{ (line1, line2) =>
  line1.substring(0, 18) < line2.substring(0, 18)
}
var currentGuard = ""
var asleepAt = 0
for(line <- sorted) {
  val a = line.split(" ")
  if(a.length == 6) {
    currentGuard = a(3).split("#").last
  } else {
    val action = a(2)
    val time = a(1).split("]").head.split(":").last.toInt
    if(action == "falls") {
      asleepAt = time
    } else {
      for(i <- asleepAt until time) {
        grid.get(currentGuard) match {
          case None => grid = grid + (currentGuard -> Seq((i, 1)))
          case Some(entries) =>
            entries.find(_._1 == i) match {
              case None =>  grid = grid + (currentGuard -> (entries :+ (i, 1)))
              case Some(entry) => grid = grid + (currentGuard -> (entries.filterNot(_._1 == i) :+ (i, entry._2 + 1)))
            }
        }
      }
    }
  }
}
val guardAsleepMostByMinute = grid.maxBy(_._2.maxBy(_._2))
guardAsleepMostByMinute._1.toInt * guardAsleepMostByMinute._2.maxBy(_._2)._1

