package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day07.txt")
    println("Day 7 Part 1: ${puzzle.day07Part1()}")
    println("Day 7 Part 2: ${puzzle.day07Part2()}")
}

fun String.day07Part1(): String {
    val manifold = parseInput(this)
    val bottom = manifold.splitters.map { it.y }.max()
    var beams = mutableSetOf<Coordinates>()
    beams.add(manifold.start)
    var splits = 0
    while (beams.all { it.y <= bottom }) {
        val newBeams = mutableSetOf<Coordinates>()
        for (beam in beams) {
            if (manifold.splitters.contains(beam)) {
                newBeams.add(Coordinates(beam.x + 1, beam.y + 1))
                newBeams.add(Coordinates(beam.x - 1, beam.y + 1))
                splits++
            } else {
                // propagate beam downwards
                newBeams.add(Coordinates(beam.x, beam.y + 1))
            }
        }
        beams = newBeams
    }
    return splits.toString()
}

fun String.day07Part2(): String {
    val manifold = parseInput(this)
    val bottom = manifold.splitters.map { it.y }.max()
    val paths = countPathsFrom(manifold.start, manifold,  mutableMapOf(), bottom)
    return paths.toString()
}

private fun countPathsFrom(point: Coordinates, manifold: TachyonManifold, cache: MutableMap<Coordinates, Long>, bottom: Int): Long {
    if (point.y > bottom) {
        return 1;
    }
    if (cache.containsKey(point)) {
        return cache[point]!!
    }
    val count = if (manifold.splitters.contains(point)) countPathsFrom(Coordinates(point.x - 1, point.y), manifold, cache, bottom) + countPathsFrom(Coordinates(point.x + 1, point.y), manifold, cache, bottom) else countPathsFrom(Coordinates(point.x, point.y + 1), manifold, cache, bottom);
    cache[point] = count;
    return count
}

private data class Coordinates(val x: Int, val y: Int)
private data class TachyonManifold(val start: Coordinates, val splitters: Set<Coordinates>)

private fun parseInput(puzzle: String): TachyonManifold {
    var start = Coordinates(0, 0);
    var splitters = mutableSetOf<Coordinates>()
    puzzle.trim().lines().forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            if (c == 'S') {
                start = Coordinates(x, y)
            } else if (c == '^') {
                splitters.add(Coordinates(x, y))
            }
        }
    }
    return TachyonManifold(start, splitters)
}
