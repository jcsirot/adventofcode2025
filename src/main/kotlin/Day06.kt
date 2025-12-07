package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day06.txt")
    println("Day 6 Part 1: ${puzzle.day06Part1()}")
    println("Day 6 Part 2: ${puzzle.day06Part2()}")
}

fun String.day06Part1(): String {
    val problems = parseInput(this)
    return problems.map { it ->
        when (it.operator) {
            "+" -> it.values.sum()
            "*" -> it.values.reduce { acc, v -> acc * v }
            else -> 0L
        }
    }.sum().toString()
}

fun String.day06Part2(): String {
    val columns = parseInput_2(this);
    val values = mutableListOf<Long>()
    var sum = 0L
    for (col in columns.reversed()) {
        //println(col)
        if (col.all { it == ' ' }) {
            values.clear()
            continue
        }
        if (col.last() == '+' || col.last() == '*') {
            val operator = col.last()
            values += col.subList(0, col.size - 1).joinToString("").trim().toLong()
            sum += when (operator) {
                '+' -> values.sum()
                '*' -> values.reduce { acc, v -> acc * v }
                else -> 0
            }
        } else {
            values += col.joinToString("").trim().toLong()
        }
    }
    return sum.toString();
}

private data class ProblemData(val values: List<Long>, val operator: String) {}

private fun parseInput(puzzle: String): List<ProblemData> {
    val lines = puzzle.trim().lines()

    val splitLines = lines.map { line ->
        line.trim().split(Regex("\\s+"))
    }

    val valueLines = splitLines.subList(0, lines.size - 1).map { it -> it.map { it.toLong() } }
    val operatorLines = splitLines[lines.size - 1]

    val columnCount = operatorLines.size

    // Construire les colonnes
    return (0 until columnCount)
        .map { col -> valueLines.map { row -> row.getOrNull(col) } }
        .mapIndexed { index, value -> ProblemData(value.filterNotNull(), operatorLines[index]) }
}

private fun parseInput_2(puzzle: String): List<List<Char>> {
    val lines = puzzle.lines()

    val height = lines.size
    val width = lines.maxOf { it.length }

    val grid = Array(height) { r ->
        CharArray(width) { c ->
            lines[r].getOrNull(c) ?: ' '
        }
    }

    return (0 until width).map { col ->
        (0 until height).map { row ->
            grid[row][col]
        }
    }
}