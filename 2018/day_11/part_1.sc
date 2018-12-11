val serial = 5177

def power(x: Int, y: Int): Int = {
  val rackID = x + 10
  var p = rackID * y
  p += serial
  p *= rackID
  p = (p / 100) % 10
  p -= 5
  p
}

def fillGrid(): Array[Array[Int]] = {
  val grid = Array.ofDim[Int](300, 300)
  for(i <- 0 until 300) {
    for(j <- 0 until 300) {
      grid(i)(j) = power(i, j)
    }
  }
  grid
}

var gridX = -1
var gridY = -1
var clusterPower = -1000
val g = fillGrid()
for(x <- 1 until 297){
  for(y <- 1 until 297){
    var p = 0
    for(i <- 0 to 3){
      for(j <- 0 to 3){
        p += g(x+i)(y+j)
      }
    }
    if (p > clusterPower) {
      gridX = x
      gridY = y
      clusterPower = p
    }
  }
}

println(s"$gridX $gridY")

var grid = fillGrid()


clusterPower = -1000
var xMax = 0
var yMax = 0
var sizeMax = 0
for(size <- 1 to 300) {
  var i = 0
  while(i < 300 - size + 1) {
    var j = 0
    while(j < 300 - size + 1) {
      var tot = 0
      var k = 0
      while(k < size) {
        var l = 0
        while(l < size) {
          tot += grid(i + k)(j + l)
          l += 1
        }
        k += 1
      }
      if(tot > clusterPower) {
        clusterPower = tot
        xMax = i
        yMax = j
        sizeMax = size
      }
      j += 1
    }
    i += 1
  }
}
println(s"$xMax $yMax $sizeMax")
