import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
fun main() {
    val time = measureTime {
        runDay1()
    }
    println("--------------\nExecution took $time")
}