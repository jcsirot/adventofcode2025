package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day03.txt")
    println("Day 3 Part 1: ${puzzle.day3Part1()}")
    println("Day 3 Part 2: ${puzzle.day3Part2()}")
}

val POWER_OF_10 = mapOf<Int, Long>(
    1 to 10L,
    2 to 100L,
    3 to 1000L,
    4 to 10000L,
    5 to 100000L,
    6 to 1000000L,
    7 to 10000000L,
    8 to 100000000L,
    9 to 1000000000L,
    10 to 10000000000L,
    11 to 100000000000L
)

fun String.day3Part1(): String {
    var banks = parseInput(this)
    var sum = 0
    banks.forEach {
        var max = 0
        for (i in it.size-2 downTo 0) {
            if ((it[i]+1)*10 > max) {
                for (j in i+1..it.size-1) {
                    val v = it[i] * 10 + it[j]
                    if (v > max) {
                        max = v
                    }
                }
            }
        }
        sum += max
    }
    return sum.toString()
}

private fun List<Int>.maxJoltage(digits: Int): Long {
    if (digits == 1) {
        return this.max().toLong()
    } else {
        var l = this.subList(0, this.size - digits + 1)
        var m = l.max()
        return m * POWER_OF_10[digits - 1]!! + this.subList(this.indexOf(m) + 1, this.size).maxJoltage(digits - 1)
    }
}

fun String.day3Part2(): String {
    var banks = parseInput(this)
    return banks.map { it.maxJoltage(digits = 12) }.sum().toString()
}

typealias Bank = List<Int>

private fun parseInput(puzzle: String): List<Bank> {
    return puzzle.trim().lines().map {
        it.toCharArray().map { it.digitToInt() }
    }
}