package org.chelonix.aoc2025

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPSolver;

fun main() {
    val puzzle = readResourceAsString("/puzzles/day10.txt")
    println("Day 10 Part 1: ${puzzle.day10Part1()}")
    println("Day 10 Part 2: ${puzzle.day10Part2()}")
}

fun String.day10Part1(): String {
    val machines = parseInput(this);
    return machines.map { machine ->
        (1 until 1.shl(machine.switches.size)).map { index ->
            val r = machine.switches.foldIndexed(0) { i, acc, switch ->
                if (index and (1 shl i) != 0) {
                    acc xor switch
                } else {
                    acc
                }
            }
            Pair(index, r)
        }.filter { it.second == machine.leds }.map { it.first.toBigInteger().bitCount() }.min()
    }.sum().toString()
}

fun String.day10Part2(): String {
    Loader.loadNativeLibraries()
    val machines = parseInput(this);
    val infinity = Double.POSITIVE_INFINITY
    return machines.map { machine ->
        val solver = MPSolver.createSolver("SCIP")
        val vars = (0 until machine.switches.size).map {solver.makeIntVar(0.0, infinity, "x" + it) }
        machine.joltage.forEachIndexed { i, joltage ->
            val c = solver.makeConstraint(joltage.toDouble(), joltage.toDouble())
            machine.switches.forEachIndexed { j, switch ->
                if (switch and (1 shl i) != 0) {
                    c.setCoefficient(vars[j], 1.0)
                }
            }
        }
        val objective = solver.objective()
        vars.forEach { objective.setCoefficient(it, 1.0) }
        val resultStatus = solver.solve()
        objective.value()
    }.sum().toInt().toString()
}

private data class Machine(val leds: Int, val switches: List<Int>, val joltage: List<Int>)

private fun parseInput(puzzle: String): List<Machine> {
    return puzzle.lineSequence()
        .map { it.trim() }
        .map { line ->
            // Extract pattern inside [ ... ]
            val regex = """^\[([.#]+)] (\(.*\)+) \{([\d,]+)}$""".toRegex()
            val matchResult = regex.find(line)!!
            val (leds, switches, joltage) = matchResult.destructured
            Machine(
                leds.ledsToInt(), switches
                    .replace("(", "")
                    .replace(")", "")
                    .split(" ")
                    .map(String::switchToPattern),
                joltage.split(",").map { it.trim().toInt() }
            )
        }.toList()
}

fun String.ledsToInt(): Int {
    return this.foldIndexed(0) { index, acc, c ->
        val bit = when (c) {
            '#' -> 1
            '.' -> 0
            else -> throw IllegalArgumentException("Invalid char: $c")
        }
        acc or (bit shl index)
    }
}

private fun String.switchToPattern(): Int {
    if (isBlank()) return 0
    return this.split(',')
        .map { it.trim() }
        .map { it.toIntOrNull() ?: throw IllegalArgumentException("Invalid number: '$it'") }
        .fold(0) { acc, idx ->
            require(idx in 0..31) { "Bit index out of range: $idx" }
            acc or (1 shl idx)
        }
}