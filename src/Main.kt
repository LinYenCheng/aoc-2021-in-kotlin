import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
fun main() {
    val time = measureTime {
        runDay1()
        runDay2()
        runDay3()
        runDay4()
        runDay5()
        runDay6()
    }
    println("--------------\nExecution took $time")
}