package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day04.txt")
    println("Day 4 Part 1: ${puzzle.day4Part1()}")
    println("Day 4 Part 2: ${puzzle.day4Part2()}")
}

fun String.day4Part1(): String {
    val grid = parseInput(this);
    var count = 0
    for (roll in grid) {
        val adjacent = grid.countAdjacent(roll)
        if (adjacent < 4) {
            count += 1
        }
    }
    return count.toString()
}

fun String.day4Part2(): String {
    var grid = parseInput(this);
    var count = 0
    val toBeRemoved = mutableSetOf<Roll>()
    while (true) {
        for (roll in grid) {
            val adjacent = grid.countAdjacent(roll)
            if (adjacent < 4) {
                toBeRemoved.add(roll)
            }
        }
        if (toBeRemoved.isEmpty()) {
            break
        }
        grid = grid.remove(toBeRemoved)
        count += toBeRemoved.size
        toBeRemoved.clear()
    }
    return count.toString()
}

private typealias Grid = List<Roll>
private fun Grid.countAdjacent(roll: Roll): Int {
    return this.count {
        (it.x == roll.x && (it.y == roll.y - 1 || it.y == roll.y + 1)) ||
        (it.y == roll.y && (it.x == roll.x - 1 || it.x == roll.x + 1)) ||
        (it.x == roll.x - 1 && (it.y == roll.y - 1 || it.y == roll.y + 1)) ||
        (it.x == roll.x + 1 && (it.y == roll.y - 1 || it.y == roll.y + 1))
    }
}

private fun Grid.remove(rolls: Collection<Roll>): Grid {
    return this.filter { roll -> !rolls.contains(roll) }
}

private data class Roll(val x: Int, val y: Int)

private fun parseInput(puzzle: String): Grid {
    return puzzle.trim().lines().flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c == '@') Roll(x, y) else null
        }
    }
}
