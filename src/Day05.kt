fun main() {

    val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

    operator fun MatchResult?.component1() = this?.destructured?.let { (moveN, _, _) ->
        moveN.toInt()
    } ?: throw IllegalArgumentException()

    operator fun MatchResult?.component2() = this?.destructured?.let { (_, from, _) ->
        from.toInt()
    } ?: throw IllegalArgumentException()

    operator fun MatchResult?.component3() = this?.destructured?.let { (_, _, to) ->
        to.toInt()
    } ?: throw IllegalArgumentException()


    fun parseStacks(input: List<String>): Map<Int, Stack> {
        val stacks = sortedMapOf<Int, Stack>()
        input.takeWhile { !it.startsWith(" 1") }.forEach { line ->
            line.chunked(4).forEachIndexed { index, c ->
                if (c.isNotBlank()) {
                    stacks.computeIfAbsent(index + 1) {
                        Stack()
                    }.add(c.trim().removeSurrounding("[", "]"))
                }
            }
        }
        return stacks
    }

    fun moveContainers(
        input: List<String>,
        stacks: Map<Int, Stack>,
        grouped: Boolean = false,
    ) {
        input.dropWhile { !it.startsWith("move") }.forEach {
            val (moveN, from, to) = regex.matchEntire(it)
            stacks[from]?.moveTo(moveN, stacks.getValue(to), grouped)
        }
    }

    fun part1(input: List<String>): String {
        val stacks = parseStacks(input)

        moveContainers(input, stacks)

        return stacks.values
            .filter { it.containers.isNotEmpty() }
            .joinToString(separator = "") { it.containers.first() }
    }


    fun part2(input: List<String>): String {
        val stacks = parseStacks(input)

        moveContainers(input, stacks, grouped = true)
        return stacks.values
            .filter { it.containers.isNotEmpty() }
            .joinToString(separator = "") { it.containers.first() }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    println(part1(readInput("Day05")))
    check(part2(testInput) == "MCD")
    println(part2(readInput("Day05")))
}

class Stack {
    var containers = mutableListOf<String>()

    fun add(container: String) {
        containers.add(container)
    }

    private fun stack(container: String) {
        containers.add(0, container)
    }

    fun moveTo(count: Int, stack: Stack, grouped: Boolean = false) {
        val take = containers.take(count)
        for (i in 0 until count) containers.removeFirst()
        if (grouped) {
            stack.containers.addAll(0, take)
        } else {
            take.forEach { stack.stack(it) }
        }
    }
}
