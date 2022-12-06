import java.util.*

fun List<Char>.hasNoDuplicates() : Boolean {
    return distinct().size == size
}

fun main() {
    fun getFirstUnique(input: String, windowSize: Int) =
        input.toCharArray().toList().windowed(windowSize).indexOfFirst { it.hasNoDuplicates() } + windowSize

    fun part1(input: String): Int {
        val windowSize = 4
        return getFirstUnique(input, windowSize)
    }

    fun part2(input: String): Int {
        val windowSize = 14
        return getFirstUnique(input, windowSize)

    }

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}
