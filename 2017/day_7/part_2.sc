import scala.io.Source
val inputStr: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_7/input").getLines().toSeq

case class Program(name: String, weight: Int, children: List[String])

val input = inputStr.map{ line =>
  val parts = line.split("->")
  val name = parts(0).split(" ").head
  val weight = parts(0).split(" ").last.trim.split("\\)").head.split("\\(").last.toInt
  val children = if(parts.length == 1) List.empty[String] else parts(1).trim.split(", ").toList
  Program(name, weight, children)
}

val root = input.find(p => !input.flatMap(_.children).toSet.contains(p.name)).get

val lookup = input.map(program => program.name -> program).toMap

def weight(p: Program): Int = p.weight + p.children.map(lookup).map(weight).sum

def findNorm(weights: List[Int]): Option[Int] =
  if (weights.toSet.size <= 1) None
  else weights match {
    case x :: y :: z :: _ => if (x == y) Some(x) else Some(z)
  }

def part2(program: Program, parentNorm: Int = 0): Int = {
  val children = program.children.map(lookup)
  findNorm(children.map(weight))
    .map(norm => part2(children.find(weight(_) != norm).get, norm))
    .getOrElse(program.weight + parentNorm - weight(program))
}
part2(root)
