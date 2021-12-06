import kotlin.math.*

fun runDay5() {
    println("--- DAY 05 ---")

    val inputTest = readInput("Day05_test")
    val input = readInput("Day05")
    check(getCountPoints(inputTest) == 5)
    check(getCountPointsWithDiagonalLines(inputTest) == 12)

    println(getCountPoints(input))
    println(getCountPointsWithDiagonalLines(input))
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
data class RevisedData(val allLines: List<Line>, val maxOfX: Int, var maxOfY: Int, var diagramArray: Array<IntArray>)

fun getRevisedData(inputTest: List<String>): RevisedData {
    val allLines = inputTest.map { l ->
        val s = l.split(" -> ")
        val (x1, y1) = s[0].split(",").map { it.toInt() }
        val (x2, y2) = s[1].split(",").map { it.toInt() }
        Line(x1, y1, x2, y2)
    }

    val maxOfX = allLines.maxOf { l -> maxOf(l.x1, l.x2) }
    val maxOfY = allLines.maxOf { l -> maxOf(l.y1, l.y2) }
    val diagramArray = Array(maxOfX + 1) { IntArray(maxOfY + 1) }

    return RevisedData(allLines, maxOfX, maxOfY, diagramArray)
}

fun getCountPoints(inputTest: List<String>): Int {
    var (allLines, maxOfX, maxOfY, diagramArray) = getRevisedData(inputTest)

    for (l in allLines) {
//        println(minOf(l.y1, l.y2))
        if (l.x1 == l.x2) {
            for (y in minOf(l.y1, l.y2)..maxOf(l.y1, l.y2))
                diagramArray[l.x1][y]++
        } else if (l.y1 == l.y2) {
            for (x in minOf(l.x1, l.x2)..maxOf(l.x1, l.x2))
                diagramArray[x][l.y1]++
        }
    }
    var ans = 0
    for (x in 0..maxOfX) for (y in 0..maxOfY) if (diagramArray[x][y] > 1) ans++
    return ans
}


//Returns the sign of this value:
//
//-1 if the value is negative,
//0 if the value is zero,
//1 if the value is positive

fun getCountPointsWithDiagonalLines(inputTest: List<String>): Int {
    var (allLines, maxOfX, maxOfY, diagramArray) = getRevisedData(inputTest)
    for (l in allLines) {
        val dx = (l.x2 - l.x1).sign
        val dy = (l.y2 - l.y1).sign
        var maxOfRange = maxOf((l.x1 - l.x2).absoluteValue, (l.y1 - l.y2).absoluteValue)
        for (t in 0..maxOfRange)
            diagramArray[l.x1 + t * dx][l.y1 + t * dy]++
    }
    var ans = 0
    for (x in 0..maxOfX) for (y in 0..maxOfY) if (diagramArray[x][y] > 1) ans++
//    println(ans)
    return ans
}
