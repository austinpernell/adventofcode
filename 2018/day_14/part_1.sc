import scala.collection.mutable.ArrayBuffer

val input = 768071

def buildRecipes(target: Int): ArrayBuffer[Int] = {
  val recipes = ArrayBuffer(3, 7)
  var p1 = 0
  var p2 = 1
  while(recipes.size < target + 10) {
    val sum: Int = recipes(p1) + recipes(p2)
    if(sum > 9) {
      recipes.append(sum / 10)
      recipes.append(sum % 10)
    }
    else{
      recipes.append(sum)
    }
    p1 = (p1 + (1 + recipes(p1))) % recipes.size
    p2 = (p2 + (1 + recipes(p2))) % recipes.size
  }
  recipes
}

buildRecipes(input).mkString("").substring(input)
buildRecipes(25000000).mkString("").indexOf(input.toString)
