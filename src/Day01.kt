import java.io.File

fun getIncreaseMeasurements(numbers: List<Int>): Int {
    var increaseMeasurements = 0
    var prevNumber = -1

    for (number in numbers) {
        if (prevNumber === -1) {
            prevNumber = number;
        } else if (number > prevNumber) {
            increaseMeasurements += 1
        }
        prevNumber = number;
    }

    return increaseMeasurements
}

fun getIncreaseMeasurementsWithSlidingWindow(numbers: List<Int>): Int {
    var increaseMeasurements = 0
    var prevSum = -1
    var nowSum = -1

    for ((index, number) in numbers.withIndex()) {
        if (prevSum === -1 && index === 2) {
            prevSum = numbers[2] + numbers[1] + numbers[0];
        }

        if (index > 2) {
            nowSum = numbers[index] + numbers[index - 1] + numbers[index - 2];
            if (nowSum > prevSum) increaseMeasurements += 1
            prevSum = nowSum
        }
    }

    return increaseMeasurements
}

fun runDay1() {
    println("--- DAY 01 ---")

    // test if implementation meets criteria from the description, like:
    val numbersTest = File("src/Day01_test.txt").readLines().map { it.toInt() }
    val numbers = File("src/Day01.txt").readLines().map { it.toInt() }
    check(getIncreaseMeasurements(numbersTest) == 7)
    check(getIncreaseMeasurementsWithSlidingWindow(numbersTest) == 5)

    println(getIncreaseMeasurements(numbers))
    println(getIncreaseMeasurementsWithSlidingWindow(numbers))
}

