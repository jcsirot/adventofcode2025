package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day05.txt")
    println("Day 5 Part 1: ${puzzle.day05Part1()}")
    println("Day 5 Part 2: ${puzzle.day05Part2()}")
}

fun String.day05Part1(): String {
    val rangesAndIds = parseInput(this)
    var count = 0
    val x = rangesAndIds.ids.map { id -> if (rangesAndIds.ranges.any { it.contains(id) }) 1 else 0 }.sum()
    for (id in rangesAndIds.ids) {
        if (rangesAndIds.ranges.any { it.contains(id) }) {
            count += 1
        }
    }
    return x.toString()
}

fun String.day05Part2(): String {
    val ranges = parseInput(this).ranges
    var sorted = ranges.sortedBy { it.min }
    while (true) {
        var untouched = true;
        for (i in 0 until sorted.size - 1) {
            val current = sorted[i]
            val next = sorted[i + 1]
            if (current.max >= next.min) {
                val merged = IdRange(current.min, maxOf(current.max, next.max))
                sorted = sorted.subList(0, i) + listOf(merged) + sorted.subList(i + 2, sorted.size)
                untouched = false
                break
            }
        }
        if (untouched) {;
            break
        }
    }
    return sorted.map { it.max-it.min + 1}.sum().toString()
}

private data class IdRange(val min: Long, val max: Long)
private fun IdRange.contains(id: Long): Boolean {
    return id in min..max
}

private data class RangesAndIds(val ranges: List<IdRange>, val ids: Set<Long>)

private fun parseInput(puzzle: String): RangesAndIds {
    val sections = puzzle.trim().split(Regex("\\R\\s*\\R"))
    val rangesPart = sections.getOrNull(0) ?: ""
    val idsPart = sections.getOrNull(1) ?: ""

    val ranges = rangesPart.lineSequence()
        .mapNotNull { line ->
            val parts = line.trim().split("-")
            IdRange(parts[0].toLong(), parts[1].toLong())
        }
        .toList()

    val ids = idsPart.lineSequence()
        .mapNotNull { it.trim().toLong() }
        .toSet()

    return RangesAndIds(ranges, ids)
}
