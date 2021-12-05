fun runDay4() {
    println("--- DAY 04 ---")

    val inputTest = readInput("Day04_test")
    val input = readInput("Day04")
    check(runPartOne(inputTest) == 4512)
    check(runPartTwo(inputTest) == 1924)

    println(runPartOne(input))
    println(runPartTwo(input))
}

fun runPartOne(input: List<String>): Any {
    val numbers = input[0].split(",").map { it.toInt() }
    class Board(lines: List<String>) {
        val arrayAllChoices = lines.map { line -> line.trim().split(" ").filter { it != "" }.map { it.toInt() } }
        val arrayMarked = Array(5) { BooleanArray(5) }
        val rows = IntArray(5)
        val columns = IntArray(5)

        fun mark(x: Int): Boolean {
            for (i in 0..4) for (j in 0..4) {
                if (arrayAllChoices[i][j] == x && !arrayMarked[i][j]) {
                    arrayMarked[i][j] = true
                    rows[i]++
                    columns[j]++
                }
            }
            // 行或列有連成一條線
            return rows.any { it == 5 } || columns.any { it == 5 }
        }

        fun countScore(): Int {
            var score = 0
            for (i in 0..4) for (j in 0..4) {
                if (!arrayMarked[i][j]) score += arrayAllChoices[i][j]
            }
            return score
        }
    }
    val boards = ArrayList<Board>()
    for (r in 2 until input.size step 6) {
        boards += Board(input.subList(r, r + 5))
    }
    var answes = 0

    x@for (x in numbers) {
        for (b in boards) {
            if (b.mark(x)) {
                answes = x * b.countScore()
                break@x
            }
        }
    }

//    println(ans)
    return answes
}

fun runPartTwo(input: List<String>): Any {
    val numbers = input[0].split(",").map { it.toInt() }
    class Board(lines: List<String>) {
        val arrayAllChoices = lines.map { line -> line.trim().split(" ").filter { it != "" }.map { it.toInt() } }
        val arrayMarked = Array(5) { BooleanArray(5) }
        val rows = IntArray(5)
        val columns = IntArray(5)

        fun mark(x: Int): Boolean {
            for (i in 0..4) for (j in 0..4) {
                if (arrayAllChoices[i][j] == x && !arrayMarked[i][j]) {
                    arrayMarked[i][j] = true
                    rows[i]++
                    columns[j]++
                }
            }
            return rows.any { it == 5 } || columns.any { it == 5 }
        }

        fun countScore(): Int {
            var score = 0
            for (i in 0..4) for (j in 0..4) {
                if (!arrayMarked[i][j]) score += arrayAllChoices[i][j]
            }
            return score
        }
    }
    val boards = ArrayList<Board>()
    for (r in 2 until input.size step 6) {
        boards += Board(input.subList(r, r + 5))
    }

    var answer = 0
    val winBoards = BooleanArray(boards.size)
    for (x in numbers) {
        for ((i, b) in boards.withIndex()) if (!winBoards[i]) {
            if (b.mark(x)) {
                winBoards[i] = true
                answer = x * b.countScore()
            }
        }
    }

//    println(answer)
    return answer
}





