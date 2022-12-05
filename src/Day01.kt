import java.util.PriorityQueue

fun main() {
    fun part1(input: List<String>): Int {
        var currentSum: Int = 0
        var maxCalories: Int = 0
        input.forEach {
            if(it.isEmpty()) {
                if (currentSum > maxCalories) {
                    maxCalories = currentSum
                }
                currentSum = 0

            } else {
                currentSum += it.toInt()
            }
        }
        return maxCalories
    }

    fun part2(input: List<String>): Int {
        var currentSum: Int = 0
        val priorityQueue = PriorityQueue<Int>(Comparator.reverseOrder())
        input.forEach {
            if(it.isEmpty()) {
                priorityQueue.offer(currentSum)
                currentSum = 0

            } else {
                currentSum += it.toInt()
            }
        }
        return (1..3).fold(0){acc, a -> acc + priorityQueue.remove()}
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)
//
    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
