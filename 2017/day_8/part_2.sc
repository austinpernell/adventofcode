import scala.io.Source
val inputStr: Seq[String] = Source.fromFile("/Users/apernel/dev/adventofcode/2017/day_8/input").getLines().toSeq

//Solution
var highestEver = 0
var registers: Map[String, Int] = Map.empty
inputStr.foreach{ line =>
  var parts = line.split(" ")
  val reg = parts.head
  parts = parts.tail
  val plusMinus = parts.head
  parts = parts.tail

  val step = parts.head.toInt
  parts = parts.tail
  parts = parts.tail

  val reg2 = parts.head
  parts = parts.tail

  val cond = parts.head
  parts = parts.tail

  val condVal = parts.head.toInt

  val foundRegVal = registers.get(reg) match {
    case None => 0
    case Some(v) => v
  }

  val foundReg2Val = registers.get(reg2) match {
    case None =>
      registers = registers + (reg2 -> 0)
      0
    case Some(v) =>
      v
  }

  cond match {
    case ">" =>
      if(foundReg2Val > condVal){
        val v = if(plusMinus == "inc") foundRegVal + step else foundRegVal - step
        registers = registers + (reg -> v)
      }
    case "<" =>
      if(foundReg2Val < condVal){
        val v = if(plusMinus == "inc") foundRegVal + step else foundRegVal - step
        registers = registers + (reg -> v)
      }
    case "<=" =>
      if(foundReg2Val <= condVal){
        val v = if(plusMinus == "inc") foundRegVal + step else foundRegVal - step
        registers = registers + (reg -> v)
      }
    case ">=" =>
      if(foundReg2Val >= condVal){
        val v = if(plusMinus == "inc") foundRegVal + step else foundRegVal - step
        registers = registers + (reg -> v)
      }
    case "!=" =>
      if(foundReg2Val != condVal){
        val v = if(plusMinus == "inc") foundRegVal + step else foundRegVal - step
        registers = registers + (reg -> v)
      }
    case "==" =>
      if(foundReg2Val == condVal){
        val v = if(plusMinus == "inc") foundRegVal + step else foundRegVal - step
        registers = registers + (reg -> v)
      }
    case somethingElse => println(s"$somethingElse !!!!!")
  }
  val currentMax = registers.maxBy(_._2)._2
  if(currentMax > highestEver){
    highestEver = currentMax
  }
}
registers.maxBy(_._2)
highestEver
