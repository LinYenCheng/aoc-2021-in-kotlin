import java.io.File

fun getDepthXHorizontal(steps: List<List<String>>): Int {
    var intHorizontal = 0
    var intDepth = 0

    for ((index, step) in steps.withIndex()) {
        when (step[0].toString()) {
            "forward" -> intHorizontal += (1 * step[1].toInt())
            "down" -> intDepth += (1 * step[1].toInt())
            "up" -> intDepth -= (1 * step[1].toInt())
        }
    }

    return intHorizontal * intDepth
}

// down X increases your aim by X units.
// up X decreases your aim by X units.
// forward X does two things:
//  It increases your horizontal position by X units.
//  It increases your depth by your aim multiplied by X.

fun getDepthXHorizontalTwo(steps: List<List<String>>): Int {
    var intHorizontal = 0
    var intDepth = 0
    var intAim = 0

    for ((index, step) in steps.withIndex()) {
        when (step[0].toString()) {
            "down" -> intAim += (1 * step[1].toInt())
            "up" -> intAim -= (1 * step[1].toInt())
            "forward" -> {
                intHorizontal += (1 * step[1].toInt())
                intDepth += intAim * step[1].toInt()
            }
        }
    }


    return intHorizontal * intDepth
}

fun runDay2() {
    println("--- DAY 02 ---")

    val stepsTest = readInput("Day02_test").map { it.toString().split(' ') }
    val steps = readInput("Day02_test").map { it.toString().split(' ') }
    check(getDepthXHorizontal(stepsTest) == 150)
    check(getDepthXHorizontalTwo(stepsTest) == 900)

    println(getDepthXHorizontal(steps))
    println(getDepthXHorizontalTwo(steps))
}

