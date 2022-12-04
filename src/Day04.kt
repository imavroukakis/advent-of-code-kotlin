fun main() {

    val regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

    operator fun MatchResult?.component1() = this?.destructured?.let { (start, end, _, _) ->
        start.toInt()..end.toInt()
    } ?: IntRange.EMPTY

    operator fun MatchResult?.component2() = this?.destructured?.let { (_, _, start, end) ->
        start.toInt()..end.toInt()
    } ?: IntRange.EMPTY

    fun part1(input: List<String>): Int {
        val solution: (String) -> Int =
            { //IDE suggestion is bad due to https://youtrack.jetbrains.com/issue/KT-46360
                val (elfOneRange, elfTwoRange) = regex.matchEntire(it)
                val union = elfOneRange.union(elfTwoRange).size
                if (elfOneRange.count() == union || elfTwoRange.count() == union) 1 else 0
            }
        return input.sumOf(solution)
    }

    fun part2(input: List<String>): Int {
        val solution: (String) -> Int =
            { //IDE suggestion is bad due to https://youtrack.jetbrains.com/issue/KT-46360
                val (elfOneRange, elfTwoRange) = regex.matchEntire(it)
                if (elfOneRange.intersect(elfTwoRange).isNotEmpty()) 1 else 0
            }
        return input.sumOf(solution)
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    println(part1(readInput("Day04")))
    check(part2(testInput) == 4)
    println(part2(readInput("Day04")))

}
