package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day09.txt")
    println("Day 8 Part 1: ${puzzle.day09Part1()}")
    println("Day 8 Part 2: ${puzzle.day09Part2()}")
}

fun String.day09Part1(): String {
    val tiles = parseInput(this)
    var maxArea = 0L
    for (i in 0 until tiles.size - 1) {
        for (j in i + 1 until tiles.size) {
            val area = rectFromTiles(tiles[i], tiles[j]).area()
            if (area > maxArea) {
                maxArea = area
            }
        }
    }
    return maxArea.toString()
}

fun String.day09Part2(): String {
    val tiles = parseInput(this)
    var maxArea = 0L
    val borders = mutableListOf<Rect>()
    for (i in 0 until tiles.size - 1) {
        borders.add(rectFromTiles(tiles[i], tiles[i + 1]))
    }
    borders.add(rectFromTiles(tiles.first(), tiles.last()))

    for (i in 0 until tiles.size - 1) {
        for (j in i + 1 until tiles.size) {
            val r = rectFromTiles(tiles[i], tiles[j])
            val intersect = borders.any { border ->
                r.contains(border)
            }
            if (!intersect) {
                val area = r.area()
                if (area > maxArea) {
                    maxArea = area
                }
            }
        }
    }
    return maxArea.toString()
}

private data class Rect(val p: Tile, val q: Tile) {
    fun area(): Long {
        return kotlin.math.abs(q.x - p.x + 1) * kotlin.math.abs(q.y - p.y + 1)
    }

    fun contains(other: Rect): Boolean {
        return p.x < other.q.x && q.x > other.p.x && p.y < other.q.y && q.y > other.p.y;
    }
}

private fun rectFromTiles(p: Tile, q: Tile): Rect {
    return Rect(
        Tile(kotlin.math.min(p.x, q.x), kotlin.math.min(p.y, q.y)),
        Tile(kotlin.math.max(p.x, q.x), kotlin.math.max(p.y, p.y))
    )
}

private data class Tile(val x: Long, val y: Long) {
    fun rect(other: Tile): Long {
        return kotlin.math.abs(x - other.x + 1) * kotlin.math.abs(y - other.y + 1)
    }
}

private fun parseInput(puzzle: String): List<Tile> {
    return puzzle.trim().lines().mapIndexed { y, line ->
        line.split(",").map { it.trim().toLong() }.let { Tile(it[0], it[1]) }
    }
}
