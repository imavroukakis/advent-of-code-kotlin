fun main() {


    fun Char.priority() = if (this.isUpperCase()) {
        this.code - 38
    } else {
        this.code - 96
    }


    fun common(input: List<Set<Char>>) = input.reduce { acc, chars ->
        if (acc.isEmpty()) {
            chars
        } else {
            chars.intersect(acc)
        }
    }.first()


    fun part1(input: List<String>): Int = input.map { line ->
        line.toList().chunked(line.length / 2).map { it.toSet() }
    }.sumOf { common(it).priority() }

    fun part2(input: List<String>): Int = input.chunked(3).map { group ->
        group.map { it.toSet() }
    }.sumOf { common(it).priority() }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    println(part1(readInput("Day03")))

    check(part2(testInput) == 70)
    println(part2(readInput("Day03")))


}
