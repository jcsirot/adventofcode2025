package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day02.txt")
    println("Day 2 Part 1: ${puzzle.day2Part1()}")
    println("Day 2 Part 2: ${puzzle.day2Part2()}")
}

fun String.day2Part1(): String {
    val bounds = parseInput(this);
    var sum = 0L
    for (bound in bounds) {
        val minSize = bound.min.length;
        val maxSize = bound.max.length;
        val min = bound.min.toLong()
        val max = bound.max.toLong()
        var ids: List<Long> = listOf()
        (minSize..maxSize).forEach {
            ids +=  when (it) {
                2 -> genPattern(1, 2)
                4 -> genPattern(2, 2)
                6 -> genPattern(3, 2)
                8 -> genPattern(4, 2)
                10 -> genPattern(5, 2)
                else -> {listOf()}
            }
        }
        for (id in ids) {
            if (id in min..max) {
                sum += id
            }
        }
    }
    return sum.toString()
}

fun String.day2Part2(): String {
    val bounds = parseInput(this);
    var sum = 0L
    for (bound in bounds) {
        val minSize = bound.min.length;
        val maxSize = bound.max.length;
        val min = bound.min.toLong()
        val max = bound.max.toLong()
        var ids: List<Long> = listOf()
        (minSize..maxSize).forEach {
            ids +=  when (it) {
                2 -> genPattern(1, 2)
                3 -> genPattern(1, 3)
                4 -> genPattern(1, 4) + genPattern(2, 2)
                5 -> genPattern(1, 5)
                6 -> genPattern(1, 6) + genPattern(2, 3) + genPattern(3, 2)
                7 -> genPattern(1, 7)
                8 -> genPattern(1, 8) + genPattern(2, 4) + genPattern(4, 2)
                9 -> genPattern(1, 9) + genPattern(3, 3)
                10 -> genPattern(1, 10) + genPattern(2, 5) + genPattern(5, 2)
                else -> {listOf()}
            }
        }
        val seen = mutableSetOf<Long>()
        for (id in ids) {
            if (id in min..max && !seen.contains(id)) {
                sum += id
                seen.add(id)
            }
        }
    }
    return sum.toString()
}

/**
 * Generate invalid product ids by generating all patterns of size `size` repeated `repeated` times.
 */
private fun genPattern(size: Int, repeated: Int): List<Long> {
    val min = Math.pow(10.0, (size - 1).toDouble()).toInt()
    val max = Math.pow(10.0, size.toDouble()).toInt() - 1
    return (min..max).map { it.toString().repeat(repeated).toLong() }
}

data class Range(val min: String, val max: String)

private fun parseInput(puzzle: String): List<Range> {
    return puzzle.split(",").map {
        val bounds = it.split("-")
        Range(bounds[0], bounds[1])
    }
}