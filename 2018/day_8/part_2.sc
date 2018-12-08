import scala.io.Source
var input: Seq[Int] = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_8/input").getLines.toSeq.head.split(" ").map(_.toInt)

//Solution
case class Tree(children: Seq[Tree], metaData: Seq[Int]) {}

def sum(tree: Tree): Int =
  tree.metaData.sum + tree.children.map(c => sum(c)).sum

def value(tree: Tree): Int =
  if(tree.children.isEmpty)
    sum(tree)
  else
    tree.metaData.flatMap { i =>
      tree.children.zipWithIndex.find { case (_, index) => (i - 1) == index }
    }.map(n => value(n._1)).sum

def parse(input: Seq[Int]): Tree = {
  val childrenCount = input.head
  val metadata = input.tail.head
  val remaining = input.tail.tail
  val children = parseChildren(childrenCount, remaining)
  val (metaData, rem2) = children.metaData.splitAt(metadata)
  Tree(Seq(Tree(children.children, metaData)), rem2)
}

def parseChildren(childrenCount: Int, input: Seq[Int]): Tree = {
  if(childrenCount == 0) {
    Tree(Seq.empty, input)
  } else {
    val parsed = parse(input)
    val children = parseChildren(childrenCount - 1, parsed.metaData)
    val combined: Seq[Tree] = parsed.children ++ children.children
    Tree(combined, children.metaData)
  }
}

value(parse(input).children.head)
