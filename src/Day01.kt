import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var sum = 0
        input.forEach {
            if (it.isNotEmpty()) {
                sum += it.toInt()
            } else {
                max = max(sum, max)
                sum = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val elfCalories = mutableListOf<Int>()
        var sum = 0
        input.forEach {
            if (it.isNotEmpty()) {
                sum += it.toInt()
            } else {
                elfCalories.add(sum)
                sum = 0
            }
        }
        elfCalories.add(sum)
        return elfCalories.sortedDescending().slice(0..2).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
