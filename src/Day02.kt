enum class MyMove(val value: String) {
    Rock("X"),
    Paper("Y"),
    Scissors("Z");

    companion object {
        infix fun from(value: String): MyMove = MyMove.values().firstOrNull { it.value == value } ?: throw Exception()
    }
}

enum class OpponentMove(val value: String) {
    Rock("A"),
    Paper("B"),
    Scissors("C");

    companion object {
        infix fun from(value: String): OpponentMove =
            OpponentMove.values().firstOrNull { it.value == value } ?: throw Exception()
    }
}

enum class DesiredOutcome(val value: String) {
    Lose("X"),
    Draw("Y"),
    Win("Z");

    companion object {
        infix fun from(value: String): DesiredOutcome =
            DesiredOutcome.values().firstOrNull { it.value == value } ?: throw Exception()
    }
}

fun getOpponentMove(input: String): OpponentMove {
    return OpponentMove.from(input.split(" ")[0])
}

fun getMyMove(input: String): MyMove {
    return MyMove.from(input.split(" ")[1])
}

fun getDesiredOutcome(input: String): DesiredOutcome {
    return DesiredOutcome.from(input.split(" ")[1])
}

fun getScore(input: String): Int {
    return getScore(getMyMove(input), getOpponentMove(input))
}

fun getScore(myMove: MyMove, opponentMove: OpponentMove): Int {
    return getWinScore(myMove, opponentMove) + getPlayScore(myMove)
}

fun getMyMoveForDesiredOutcome(input: String): MyMove {
    return getMyMoveForDesiredOutcome(getOpponentMove(input), getDesiredOutcome((input)))
}


fun getMyMoveForDesiredOutcome(opponentMove: OpponentMove, desiredOutcome: DesiredOutcome): MyMove {
    return when (opponentMove) {
        OpponentMove.Rock -> when (desiredOutcome) {
            DesiredOutcome.Lose -> MyMove.Scissors
            DesiredOutcome.Draw -> MyMove.Rock
            DesiredOutcome.Win -> MyMove.Paper
        }

        OpponentMove.Paper -> when (desiredOutcome) {
            DesiredOutcome.Lose -> MyMove.Rock
            DesiredOutcome.Draw -> MyMove.Paper
            DesiredOutcome.Win -> MyMove.Scissors
        }

        OpponentMove.Scissors -> when (desiredOutcome) {
            DesiredOutcome.Lose -> MyMove.Paper
            DesiredOutcome.Draw -> MyMove.Scissors
            DesiredOutcome.Win -> MyMove.Rock
        }
    }
}

fun getPlayScore(myMove: MyMove): Int {
    return when (myMove) {
        MyMove.Rock -> 1
        MyMove.Paper -> 2
        MyMove.Scissors -> 3
    }
}

fun getWinScore(myMove: MyMove, opponentMove: OpponentMove): Int {
    return when (myMove) {
        MyMove.Rock -> when (opponentMove) {
            OpponentMove.Rock -> 3
            OpponentMove.Paper -> 0
            OpponentMove.Scissors -> 6
        }

        MyMove.Paper -> when (opponentMove) {
            OpponentMove.Rock -> 6
            OpponentMove.Paper -> 3
            OpponentMove.Scissors -> 0
        }

        MyMove.Scissors -> when (opponentMove) {
            OpponentMove.Rock -> 0
            OpponentMove.Paper -> 6
            OpponentMove.Scissors -> 3
        }
    }

}

fun main() {
    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, current ->
            acc + getScore(current)
        }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            Pair(getOpponentMove(it), getMyMoveForDesiredOutcome(it))
        }.fold(0) { acc, current: Pair<OpponentMove, MyMove> ->
            acc + getScore(current.second, current.first)
        }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
