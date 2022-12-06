fun getRange(input: String): IntRange {
    val split = input.split("-")
    return (split[0].toInt()..split[1].toInt())
}

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { it.split(",") }
            .map { Pair(getRange(it[0]), getRange(it[1])) }
            .fold(0) { acc, current ->
                if (current.first.all { current.second.contains(it) } ||
                    current.second.all { current.first.contains(it) })
                    acc + 1
                else
                    acc
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(",") }
            .map { Pair(getRange(it[0]), getRange(it[1])) }
            .fold(0) { acc, current -> if (current.first.any { current.second.contains(it) }) acc + 1 else acc }

    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
