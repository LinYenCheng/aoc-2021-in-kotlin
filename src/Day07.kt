import kotlin.math.*

fun runDay7() {
    println("--- DAY 07 ---")

    val inputTest = readInput("Day07_test")[0].split(",").map { it.toInt() }
    val input = readInput("Day07")[0].split(",").map { it.toInt() }
    check(getFuel(inputTest) == 37)
    check(getFuelWithMoreCost(inputTest) == 168)

    println(getFuel(input))
    println(getFuelWithMoreCost(input))
}

fun getFuel(crabPositions: List<Int>): Int {
    var minSum = Int.MAX_VALUE

    for (i in 0..crabPositions.maxOrNull()!!) {
        val sumOfValueToI = crabPositions.sumOf { x -> abs(i - x) }
        if (sumOfValueToI < minSum) minSum = sumOfValueToI
    }
//    println(minSum)

    return minSum
}

fun getFuelWithMoreCost(crabPositions: List<Int>): Int {
    var minSum = Int.MAX_VALUE

    for (i in 0..crabPositions.maxOrNull()!!) {
        val sumOfValueToI = crabPositions.sumOf { x ->
            var distance = abs(i - x)
            distance.toLong() * (distance + 1) / 2
        }
        if (sumOfValueToI < minSum) minSum = sumOfValueToI.toInt()
    }
//    println(minSum)

    return minSum
}
