import java.util.*

data class CraneCommand(val quantity: Int, val from: Int, val to: Int)

fun MutableMap<Int, Stack<Char>>.processCraneCommand(command: CraneCommand): MutableMap<Int, Stack<Char>> {
    val fromStack = get(command.from)
    val toStack = get(command.to)
    for (i in 0 until command.quantity) {
        toStack!!.push(fromStack!!.pop()!!)
    }
    return this
}

fun MutableMap<Int, Stack<Char>>.processBatchCraneCommand(command: CraneCommand): MutableMap<Int, Stack<Char>> {
    val tempStack = Stack<Char>()
    val fromStack = get(command.from)
    val toStack = get(command.to)
    for (i in 0 until command.quantity) {
        tempStack.push(fromStack!!.pop()!!)
    }
    for (i in 0 until command.quantity) {
        toStack!!.push(tempStack.pop()!!)
    }
    return this
}

fun MutableMap<Int, Stack<Char>>.getResult(): String {
    return String(keys.sorted().map { get(it)!!.pop() }.toCharArray())
}

fun getCraneCommand(input: String): CraneCommand {
    val split = input.split("from")
    val quantity = split[0].split(" ")[1].trim().toInt()
    val (from, to) = split[1].split("to").map { it.trim().toInt() }
    return CraneCommand(quantity, from, to)
}

fun getLabelPositionMap(input: List<String>, index: Int): Map<Int, Int> {
    val mutableMap = mutableMapOf<Int, Int>()
    val labels = input.get(index)
    for (i in (labels.indices)) {
        if (labels[i].isDigit()) {
            mutableMap[i] = labels[i].digitToInt()
        }
    }
    return mutableMap.toMap()
}

fun getCharStackMap(labelPositionMap: Map<Int, Int>, characters: String): Map<Int, Char> {
    val mutableMap = mutableMapOf<Int, Char>()
    for (i in (characters.indices)) {
        if (characters[i].isLetter()) {
            mutableMap[labelPositionMap[i]!!] = characters[i]
        }
    }
    return mutableMap
}

fun getStackMap(input: List<String>, indexOfLabelRow: Int): MutableMap<Int, Stack<Char>> {
    val stackMap = mutableMapOf<Int, Stack<Char>>()
    val labelPositionMap = getLabelPositionMap(input, indexOfLabelRow)
    for (i in (indexOfLabelRow - 1 downTo 0)) {
        val charStackMap = getCharStackMap(labelPositionMap, input[i])
        for (j in charStackMap.keys) {
            val stack: Stack<Char> = stackMap.getOrPut(j) { Stack<Char>() }
            stack.push(charStackMap[j])

        }
    }
    return stackMap
}

fun main() {
    fun part1(input: List<String>): String {
        val indexOfLabelRow = input.indexOfFirst { it.all { it.isDigit() || it.isWhitespace() } }
        val stackMap = getStackMap(input, indexOfLabelRow)
        (indexOfLabelRow + 2 until input.size)
            .map { getCraneCommand(input[it]) }
            .fold(stackMap) { acc, craneCommand -> acc.processCraneCommand(craneCommand) }
        return stackMap.getResult()
    }

    fun part2(input: List<String>): String {
        val indexOfLabelRow = input.indexOfFirst { it.all { it.isDigit() || it.isWhitespace() } }
        val stackMap = getStackMap(input, indexOfLabelRow)
        (indexOfLabelRow + 2 until input.size)
            .map { getCraneCommand(input[it]) }
            .fold(stackMap) { acc, craneCommand -> acc.processBatchCraneCommand(craneCommand) }
        return stackMap.getResult()
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
