const val PART_ONE_DAYS = 80
const val PART_TWO_DAYS = 256

fun runDay6() {
    println("--- DAY 06 ---")

    val inputTest = readInput("Day06_test")
    val input = readInput("Day06")
    check(getLanternFish(PART_ONE_DAYS, inputTest) == 5934.toLong())
    check(getLanternFish(PART_TWO_DAYS, inputTest) == 26984457539.toLong())

    println(getLanternFish(PART_ONE_DAYS, input))
    println(getLanternFish(PART_TWO_DAYS, input))
}

fun getLanternFish(intDays: Int, fish: List<String>): Long {
    var totalFish = fish[0].split(",").map { it.toInt() }
    var staticCountArray = LongArray(9)
    for (x in totalFish) staticCountArray[x]++
    
    repeat(intDays) {
        val countFish = LongArray(9)
        for (fish in 0..8) {
            when {
                fish > 0 -> countFish[fish - 1] += staticCountArray[fish]
                fish == 0 -> {
                    countFish[6] += staticCountArray[fish]
                    countFish[8] += staticCountArray[fish]
                }
            }
        }
        staticCountArray = countFish
    }

//    println(staticCountArray.sum())
    return staticCountArray.sum()
}