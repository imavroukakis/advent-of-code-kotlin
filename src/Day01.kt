import java.io.File

fun main() {
    fun elfData(input: String): List<List<Int>> {
        val data = input.split("\n\n").map { elf ->
            elf.lines().map { it.toInt() }
        }
        return data
    }

    fun part1(input: String): Int {
        return elfData(input).maxOf { it.sum() }
    }

    fun part2(input: String): Int {
        return elfData(input).sortedByDescending { it.sum() }.take(3).sumOf { it.sum() }
    }

    check(part2(File("src/Day01_test.txt").readText()) == 45000)
    check(part1(File("src/Day01_test.txt").readText()) == 24000)

    println(part1(File("src/Day01.txt").readText()))
    println(part2(File("src/Day01.txt").readText()))
}
