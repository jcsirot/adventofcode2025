import org.chelonix.aoc2025.day3Part1
import org.chelonix.aoc2025.day3Part2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun day3Part1() {
        val sum = """
987654321111111
811111111111119
234234234234278
818181911112111""".day3Part1()
        assertEquals("357", sum)
    }

    @Test
    fun day3Part2() {
        val sum = """
987654321111111
811111111111119
234234234234278
818181911112111""".day3Part2()
        assertEquals("3121910778619", sum)

    }

}