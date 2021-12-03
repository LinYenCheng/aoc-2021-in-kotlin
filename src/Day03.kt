fun runDay3() {
    println("--- DAY 03 ---")

    val diagnosticsTest = readInput("Day03_test").map { it.toString().toList() }
    val diagnostics = readInput("Day03").map { it.toString().toList() }
    check(getPowerConsumption(diagnosticsTest) == 198)
    check(getLifeSupportRating(diagnosticsTest) == 230)

    println(getPowerConsumption(diagnostics))
    println(getLifeSupportRating(diagnostics))
}

fun getFilteredDiagnostics(diagnostics: List<List<Char>>, intIndex: Int, filteredValue: Char): Any {
    var filteredDiagnostics = diagnostics.filter { it[intIndex] == filteredValue }
    return filteredDiagnostics
}


fun getLifeSupportRating(diagnostics: List<List<Char>>): Any {
    var filteredDiagnosticsForMostCommon = diagnostics
    var filteredDiagnosticsForLeastCommon = diagnostics
    var arrayCountOfZero = IntArray(diagnostics[0].size) { 0 }
    var arrayCountOfOne = IntArray(diagnostics[0].size) { 0 }
    var arrayResultMostCommon = CharArray(diagnostics[0].size)
    var arrayResultLeastCommon = CharArray(diagnostics[0].size)


    for (index in arrayResultMostCommon.indices) {
        if (filteredDiagnosticsForMostCommon.size > 1) {

            arrayCountOfZero = IntArray(diagnostics[0].size) { 0 }
            arrayCountOfOne = IntArray(diagnostics[0].size) { 0 }
            arrayResultMostCommon = CharArray(diagnostics[0].size)
            arrayResultLeastCommon = CharArray(diagnostics[0].size)

            for (diagnostic in filteredDiagnosticsForMostCommon) {
                for ((index, bit) in diagnostic.withIndex()) {
                    if (bit == '0') {
                        arrayCountOfZero[index] += 1
                    } else {
                        arrayCountOfOne[index] += 1
                    }
                }
            }

            for (index in arrayResultMostCommon.indices) {
                if (arrayCountOfZero[index] > arrayCountOfOne[index]) {
                    arrayResultMostCommon[index] = '0'
                    arrayResultLeastCommon[index] = '1'
                } else if (arrayCountOfZero[index] == arrayCountOfOne[index]) {
                    arrayResultMostCommon[index] = '1'
                    arrayResultLeastCommon[index] = '0'
                } else {
                    arrayResultMostCommon[index] = '1'
                    arrayResultLeastCommon[index] = '0'
                }
            }

            filteredDiagnosticsForMostCommon =
                getFilteredDiagnostics(
                    filteredDiagnosticsForMostCommon,
                    index,
                    arrayResultMostCommon[index]
                ) as List<List<Char>>
        }
    }

    for (index in arrayResultMostCommon.indices) {
        if (filteredDiagnosticsForLeastCommon.size > 1) {

            arrayCountOfZero = IntArray(diagnostics[0].size) { 0 }
            arrayCountOfOne = IntArray(diagnostics[0].size) { 0 }
            arrayResultMostCommon = CharArray(diagnostics[0].size)
            arrayResultLeastCommon = CharArray(diagnostics[0].size)

            for (diagnostic in filteredDiagnosticsForLeastCommon) {
                for ((index, bit) in diagnostic.withIndex()) {
                    if (bit == '0') {
                        arrayCountOfZero[index] += 1
                    } else {
                        arrayCountOfOne[index] += 1
                    }
                }
            }

            for (index in arrayResultMostCommon.indices) {
                if (arrayCountOfZero[index] > arrayCountOfOne[index]) {
                    arrayResultMostCommon[index] = '0'
                    arrayResultLeastCommon[index] = '1'
                } else if (arrayCountOfZero[index] == arrayCountOfOne[index]) {
                    arrayResultMostCommon[index] = '1'
                    arrayResultLeastCommon[index] = '0'
                } else {
                    arrayResultMostCommon[index] = '1'
                    arrayResultLeastCommon[index] = '0'
                }
            }

            filteredDiagnosticsForLeastCommon =
                getFilteredDiagnostics(
                    filteredDiagnosticsForLeastCommon,
                    index,
                    arrayResultLeastCommon[index]
                ) as List<List<Char>>
        }
    }


    return Integer.parseInt(filteredDiagnosticsForMostCommon[0].toCharArray().concatToString(), 2) * Integer.parseInt(
        filteredDiagnosticsForLeastCommon[0].toCharArray().concatToString(),
        2
    )
}

fun getPowerConsumption(diagnostics: List<List<Char>>): Any {
    var arrayCountOfZero = IntArray(diagnostics[0].size) { 0 }
    var arrayCountOfOne = IntArray(diagnostics[0].size) { 0 }
    var arrayResultGamma = CharArray(diagnostics[0].size)
    var arrayResultEpsilon = CharArray(diagnostics[0].size)
    var strResultGamma = ""
    var strResultEpsilon = ""

    for (diagnostic in diagnostics) {
        for ((index, bit) in diagnostic.withIndex()) {
            if (bit == '0') {
                arrayCountOfZero[index] += 1
            } else {
                arrayCountOfOne[index] += 1
            }
        }
    }

    for (index in arrayResultGamma.indices) {
        if (arrayCountOfZero[index] > arrayCountOfOne[index]) {
            arrayResultGamma[index] = '0'
            arrayResultEpsilon[index] = '1'
        } else {
            arrayResultGamma[index] = '1'
            arrayResultEpsilon[index] = '0'
        }
    }

    strResultGamma = arrayResultGamma.concatToString();
    strResultEpsilon = arrayResultEpsilon.concatToString();

    var intResultGamma = Integer.parseInt(strResultGamma, 2)
    var intResultEpsilon = Integer.parseInt(strResultEpsilon, 2)

    return intResultGamma * intResultEpsilon
}

