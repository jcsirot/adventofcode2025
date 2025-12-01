package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day01.txt")
    println("Day 1 Part 1: ${puzzle.day1Part1()}")
    println("Day 1 Part 2: ${puzzle.day1Part2()}")
}

fun String.day1Part1(): String {
    val moves = parseInput(this)
    var pointer = 50
    var password = 0
    for (move in moves) {
        pointer = (pointer + (if (move.direction == Direction.RIGHT) 1 else -1) * move.distance).mod(100)
        password += if (pointer == 0) 1 else 0
    }
    return password.toString()
}

fun String.day1Part2(): String {
    val moves = parseInput(this)
    var pointer = 50
    var password = 0
    for (move in moves) {
        val step = if (move.direction == Direction.RIGHT) 1 else -1
        for (i in 1..move.distance) {
            pointer = (pointer + step).mod(100)
            if (pointer == 0) {
                password += 1
            }
        }
    }
    return password.toString()
}

enum class Direction {
    LEFT, RIGHT
}

data class Move(val direction: Direction, val distance: Int)

private fun parseInput(puzzle: String): List<Move> {
    return puzzle.lines().filter { it.isNotBlank() }
        .map(String::trim)
        .map {
        val dir = when (it[0]) {
            'L' -> Direction.LEFT
            'R' -> Direction.RIGHT
            else -> throw IllegalArgumentException("Invalid direction: ${it[0]}")
        }
        val distance = it.substring(1).toInt()
        Move(dir, distance)
    }
}