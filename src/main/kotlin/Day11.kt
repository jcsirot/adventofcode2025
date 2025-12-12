package org.chelonix.aoc2025

fun main() {
    val puzzle = readResourceAsString("/puzzles/day11.txt")
    println("Day 11 Part 1: ${puzzle.day11Part1()}")
    println("Day 11 Part 2: ${puzzle.day11Part2()}")
}

fun String.day11Part1(): String {
    val graph = parseInput(this);
    return return countPaths(graph, "you", "out").toString()
}

fun String.day11Part2(): String {
    val graph = parseInput(this)
    val fft2dac = countPaths(graph, "fft", "dac")
    val dac2fft = countPaths(graph, "dac", "fft")
    if(fft2dac > 0) { // fft -> dac exists
        return (countPaths(graph, "svr", "fft") * countPaths(graph, "dac", "out") * fft2dac).toString()
    } else { // dac -> fft exists
        return (countPaths(graph, "svr", "dac") * countPaths(graph, "fft", "out") * dac2fft).toString()
    }
}

private fun countPaths(graph: Graph, start: String, end: String): Long {
    val paths = graph.sort.associateWith { 0L }.toMutableMap()
    paths[start] = 1L
    for (node in graph.sort) {
        for (neighbour in graph.successors(node)) {
            paths[neighbour] = paths[neighbour]?.plus(paths[node] ?: 0) as Long
        }
    }
    return paths[end] ?: 0L

}

private data class Graph(val edges: List<Pair<String,String>>, val sort: List<String>) {
    fun successors(node: String): List<String> {
        return edges.filter { it.first == node }.map { it.second }
    }
}

fun topologicalSort(edges: List<Pair<String, String>>): List<String> {
    val nodes = mutableSetOf<String>()
    edges.forEach { (x,y) -> nodes.add(x); nodes.add(y) }

    val adj = nodes.associateWith { mutableListOf<String>() }.toMutableMap()
    val degree = nodes.associateWith { 0 }.toMutableMap()

    edges.forEach { (x, y) -> adj[x]!!.add(y); degree[y] = degree[y]!! + 1 }

    val queue = ArrayDeque<String>()
    nodes.filter { degree[it] == 0 }.forEach { queue.add(it) }
    val topologicalSort = mutableListOf<String>()
    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()
        topologicalSort.add(u)

        adj[u]!!.forEach {
            degree[it] = degree[it]!! - 1
            if (degree[it] == 0) queue.add(it)
        }
    }
    if (topologicalSort.size != nodes.size) {
        error("Cycle found. NOT A DAG!!!")
    }
    return topologicalSort
}

private fun parseInput(puzzle: String): Graph {
    val edges = puzzle.lineSequence()
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .flatMap { line ->
            val parts = line.split(":").map { it.trim() }
            val node = parts[0]
            parts[1].split(" ").map { Pair(node, it) }.toList()
        }.toList()
    return Graph(edges, topologicalSort(edges))
}