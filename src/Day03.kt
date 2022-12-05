val priorities = ('a'..'z') + ('A'..'Z')

fun String.getPriority(priorities: List<Char>): Int {
    val firstCompartment = substring(0 until (length / 2)).toSet()
    val secondCompartment = substring(length / 2 until length)
    return priorities.indexOf(secondCompartment.first { firstCompartment.contains(it) }) + 1
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, current -> acc + current.getPriority(priorities) }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toCharArray() }
            .chunked(3)
            .fold(0) { acc, current ->
                acc + priorities.indexOf(current.reduce { ac, s -> ac.intersect(s.asIterable().toSet()).toCharArray() }.first()) + 1
            }

    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
