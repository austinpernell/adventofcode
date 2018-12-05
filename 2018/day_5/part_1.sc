import scala.io.Source
var inputStr: String = Source.fromFile("/Users/apernel/dev/adventofcode/2018/day_5/input").getLines.toSeq.head

//Solution
var flag = true
while(flag){
  var i: Int = -1
  for(at <- 0 until inputStr.length){
    if(i == -1 && at + 1 != inputStr.length && ((inputStr(at).isLower && inputStr(at + 1).isUpper && inputStr(at).toUpper == inputStr(at + 1)) ||
      (inputStr(at).isUpper && inputStr(at + 1).isLower && inputStr(at).toLower == inputStr(at + 1)))) {
      i = at
    }
  }
  if(i != -1){
    if(i == 0){
      inputStr = inputStr.substring(i + 2, inputStr.length)
    } else if(i + 1 == inputStr.length){
      inputStr = inputStr.substring(0, i - 1)
    } else {
      inputStr = inputStr.substring(0, i ) + inputStr.substring(i + 2, inputStr.length)
    }
  } else {
    flag = false
  }
}
inputStr.length
