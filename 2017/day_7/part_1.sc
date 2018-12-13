import scala.io.Source
val inputStr: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_7/input").getLines().toSeq

case class Program(name: String, weight: Int, children: Seq[String])

val input = inputStr.map{ line =>
  val parts = line.split("->")
  val name = parts(0).split(" ").head
  val weight = parts(0).split(" ").last.trim.split("\\)").head.split("\\(").last.toInt
  val children = if(parts.length == 1) Seq.empty else parts(1).trim.split(", ").toSeq
  Program(name, weight, children)
}

input.find(p => !input.flatMap(_.children).toSet.contains(p.name)).get

