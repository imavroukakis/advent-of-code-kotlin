fun main() {


    fun findMark(input: String, window: Int, partial: Boolean = false): Int {
        var count = window
        input.windowed(size = window, step = 1, partialWindows = partial).forEach {
            if (it.toSet().size != window) {
                count++
            } else {
                return count
            }
        }
        return count
    }

    fun part1(input: List<String>): Int {
        return findMark(input[0], 4)
    }


    fun part2(input: List<String>): Int {
        return findMark(input[0], 14)
    }

    val testInput = readInput("Day06_test")
    val testMarker = part1(testInput)
    check(testMarker == 7)
    val marker = part1(readInput("Day06"))
    println(marker)
    check(part2(testInput) == 19)
    println(part2(readInput("Day06")))
}
