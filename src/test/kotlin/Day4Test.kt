import org.chelonix.aoc2025.day4Part1
import org.chelonix.aoc2025.day4Part2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun day4Part1() {
        val count = """
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.""".day4Part1()
        assertEquals("13", count)
    }

    @Test
    fun day4Part2() {
        val count = """
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.""".day4Part2()
        assertEquals("43", count)
    }

}