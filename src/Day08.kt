fun main() {


    fun topVisible(
        outerIdx: Int,
        map: List<List<Int>>,
        innerIdx: Int,
        v: Int,
    ): Pair<Boolean, Int> {
        var l = outerIdx

        var score = 0
        while (l >= 0) {
            val element = map[l--][innerIdx]
            score++
            if (v <= element) return Pair(false, score)
        }
        return Pair(true, score)
    }

    fun bottomVisible(
        outerIdx: Int,
        map: List<List<Int>>,
        innerIdx: Int,
        v: Int,
    ): Pair<Boolean, Int> {
        var l = outerIdx

        var score = 0
        while (l <= map.size - 1) {
            val element = map[l++][innerIdx]
            score++
            if (v <= element) return Pair(false, score)
        }
        return Pair(true, score)
    }

    fun leftVisible(
        outerIdx: Int,
        map: List<List<Int>>,
        innerIdx: Int,
        v: Int,
    ): Pair<Boolean, Int> {
        var l = innerIdx

        var score = 0
        while (l >= 0) {
            val element = map[outerIdx][l--]
            score++
            if (v <= element) return Pair(false, score)
        }
        return Pair(true, score)
    }

    fun rightVisible(
        outerIdx: Int,
        map: List<List<Int>>,
        innerIdx: Int,
        v: Int,
    ): Pair<Boolean, Int> {
        var l = innerIdx

        var score = 0
        while (l < map[outerIdx].size - 1) {
            val element = map[outerIdx][++l]
            score++
            if (v <= element) return Pair(false, score)
        }
        return Pair(true, score)
    }


    fun doScore(map: List<List<Int>>) =
        map.drop(1).dropLast(1).mapIndexed { outerIdx, line ->
            val dropLast = line.drop(1).dropLast(1)
            dropLast.mapIndexed { innerIdx, v ->
                listOf(
                    topVisible(outerIdx, map, innerIdx + 1, v),
                    bottomVisible(outerIdx + 2, map, innerIdx + 1, v),
                    leftVisible(outerIdx + 1, map, innerIdx, v),
                    rightVisible(outerIdx + 1, map, innerIdx + 1, v)
                )
            }
        }.flatten()

    fun part1(input: List<String>): Int {
        val map = input.map {
            it.toList().map { it.digitToInt() }
        }
        val boundaryCount = ((map.first().size - 2) * 4) + 4
        val edgeVisibleTrees = doScore(map).map {
            val (topVisible, bottomVisible, leftVisible, rightVisible) = it
            if (topVisible.first || bottomVisible.first || leftVisible.first || rightVisible.first) {
                1
            } else 0
        }.sum()
        return edgeVisibleTrees + boundaryCount
    }


    fun part2(input: List<String>): Int {
        val map = input.map {
            it.toList().map { it.digitToInt() }
        }
        val mostTreesVisible =doScore(map).map {
            val (topVisible, bottomVisible, leftVisible, rightVisible) = it
            topVisible.second * bottomVisible.second * leftVisible.second * rightVisible.second

        }.max()
        return mostTreesVisible
    }

    val testInput = readInput("Day08_test")
    var test = part1(testInput)
    check(test == 21)
    println(part1(readInput("Day08")))
    test = part2(testInput)
    check(test == 8)
    println(part2(readInput("Day08")))
}
