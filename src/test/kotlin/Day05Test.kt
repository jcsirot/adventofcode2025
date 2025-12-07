import org.chelonix.aoc2025.day05Part1
import org.chelonix.aoc2025.day05Part2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {
    @Test
    fun day5Part1() {
        val count = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32""".day05Part1()
        assertEquals("3", count)
    }

    @Test
    fun day5Part2() {
        val count = """
        3-5
        10-14
        16-20
        12-18

        1
        5
        8
        11
        17
        32""".day05Part2()
        assertEquals("14", count)
    }

}