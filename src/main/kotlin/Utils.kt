package org.chelonix.aoc2025

fun readResourceAsString(path: String): String {
    val url = object {}.javaClass.getResource(path)
        ?: throw IllegalArgumentException("Resource not found: $path")
    return url.readText()
}