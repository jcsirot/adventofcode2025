import org.chelonix.aoc2025.day06Part1
import org.chelonix.aoc2025.day06Part2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {
    @Test
    fun day06Part1() {
        val sum = """
        123 328  51 64 
        45 64  387 23 
  　　　　6 98  215 314
        *   +   *   +    
        """.trimIndent().day06Part1()
        assertEquals("4277556", sum)
    }

    @Test
    fun day06Part2() {
        val sum =
"""123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +  
""".trimIndent().day06Part2()
        assertEquals("3263827", sum)
    }

}