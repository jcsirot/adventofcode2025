package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day08.txt")
    println("Day 8 Part 1: ${puzzle.day08Part1(1000)}")
    println("Day 8 Part 2: ${puzzle.day08Part2()}")
}

fun String.day08Part1(len: Int): String {
    val boxes = parseInput(this)
    val connection = connections(boxes);
    val circuits = mutableListOf<Set<Box>>()
    for (i in 0..len) {
        val c = connection[i]
        val set1 = circuits.find { it.contains(c.box1) }
        val set2 = circuits.find { it.contains(c.box2) }
        if (set1 == null && set2 == null) {
            circuits.add(setOf(c.box1, c.box2))
        } else if (set1 != null && set2 == null) {
            val newSet = set1.toMutableSet()
            newSet.add(c.box2)
            circuits.remove(set1)
            circuits.add(newSet)
        } else if (set1 == null && set2 != null) {
            val newSet = set2.toMutableSet()
            newSet.add(c.box1)
            circuits.remove(set2)
            circuits.add(newSet)
        } else if (set1 != set2) {
            val newSet = set1!!.toMutableSet()
            newSet.addAll(set2!!)
            circuits.remove(set1)
            circuits.remove(set2)
            circuits.add(newSet)
        }
    }
    return circuits.sortedByDescending { it.size }.take(3).map { it.size }.reduce { a, b -> a * b }.toString()
}

fun String.day08Part2(): String {
    val boxes = parseInput(this)
    val connection = connections(boxes);
    val circuits = mutableListOf<Set<Box>>()
    for (i in 0..connection.size) {
        val c = connection[i]
        val set1 = circuits.find { it.contains(c.box1) }
        val set2 = circuits.find { it.contains(c.box2) }
        if (set1 == null && set2 == null) {
            circuits.add(setOf(c.box1, c.box2))
        } else if (set1 != null && set2 == null) {
            val newSet = set1.toMutableSet()
            newSet.add(c.box2)
            circuits.remove(set1)
            circuits.add(newSet)
        } else if (set1 == null && set2 != null) {
            val newSet = set2.toMutableSet()
            newSet.add(c.box1)
            circuits.remove(set2)
            circuits.add(newSet)
        } else if (set1 != set2) {
            val newSet = set1!!.toMutableSet()
            newSet.addAll(set2!!)
            circuits.remove(set1)
            circuits.remove(set2)
            circuits.add(newSet)
        }
        if (circuits.size == 1 && circuits[0].size == boxes.size) {
            return (c.box1.x * c.box2.x).toString()
        }
    }
    return ""
}

private data class Box(val x: Long, val y: Long, val z: Long) {
    fun sqDistance(other: Box): Long {
        return (x - other.x) * (x - other.x) +
               (y - other.y) * (y - other.y) +
               (z - other.z) * (z - other.z)
    }
}

private data class Connection(val box1: Box, val box2: Box, val distance: Long)

private fun connections(boxes: List<Box>): List<Connection> {
    val list = mutableListOf<Connection>()
    for (i in boxes.indices) {
        for (j in i + 1 until boxes.size) {
            val distance = boxes[i].sqDistance(boxes[j])
            list.add(Connection(boxes[i], boxes[j], distance))
        }
    }
    return list.sortedBy { it.distance }
}

private fun parseInput(puzzle: String): List<Box> {
    return puzzle.trim().lines().map { line ->
        line.split(",").map { it.toLong() } }.map { Box(it[0], it[1], it[2]) }
}
