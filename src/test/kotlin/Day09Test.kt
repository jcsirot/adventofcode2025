import org.chelonix.aoc2025.day09Part1
import org.chelonix.aoc2025.day09Part2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day09Test {
    @Test
    fun day09Part1() {
        val area = """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
        """.trimIndent().day09Part1()
        assertEquals("50", area)
    }

    @Test
    fun day09Part2() {
        val area ="""
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
        """.trimIndent().day09Part2()
        assertEquals("24", area)
    }
}