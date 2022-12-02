fun main() {


    val handLookup = mapOf(
        "A" to Rock,
        "B" to Paper,
        "C" to Scissors,
        "X" to Rock,
        "Y" to Paper,
        "Z" to Scissors,
    )

    fun toHand(hand: String): Hand = handLookup.getValue(hand)


    fun part1(input: List<String>): Int {
        return input.sumOf {
            val split = it.split(" ")
            val opponentHand = toHand(split[0])
            val playerHand = toHand(split[1])
            playerHand.pointsAgainst(opponentHand) + playerHand.value()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val split = it.split(" ")
            val opponentHand = toHand(split[0])
            val playerHand = when (split[1]) {
                "X" -> opponentHand.defeats()
                "Y" -> opponentHand
                "Z" -> opponentHand.loses()
                else -> throw IllegalArgumentException()
            }
            playerHand.pointsAgainst(opponentHand) + playerHand.value()
        }
    }


    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    println(part1(readInput("Day02")))

    check(part2(testInput) == 12)
    println(part2(readInput("Day02")))

}

sealed interface Hand {
    fun pointsAgainst(hand: Hand): Int
    fun value(): Int
    fun defeats(): Hand
    fun loses(): Hand
}

object Rock : Hand {
    override fun pointsAgainst(hand: Hand): Int = when (hand) {
        is Rock -> 3
        is Paper -> 0
        is Scissors -> 6
    }

    override fun value(): Int = 1

    override fun defeats(): Hand = Scissors

    override fun loses(): Hand = Paper
}

object Paper : Hand {
    override fun pointsAgainst(hand: Hand): Int = when (hand) {
        is Rock -> 6
        is Paper -> 3
        is Scissors -> 0
    }

    override fun value(): Int = 2

    override fun defeats(): Hand = Rock

    override fun loses(): Hand = Scissors
}

object Scissors : Hand {
    override fun pointsAgainst(hand: Hand): Int = when (hand) {
        is Rock -> 0
        is Paper -> 6
        is Scissors -> 3
    }

    override fun value(): Int = 3

    override fun defeats(): Hand = Paper

    override fun loses(): Hand = Rock
}

