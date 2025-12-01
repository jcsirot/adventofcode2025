import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun day1Part1() {
        val password = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82""".day1Part1()
        Assertions.assertEquals("3", password)
    }

    @Test
    fun day1Part2() {
        val password = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82""".day1Part2()
        Assertions.assertEquals("6", password)
    }

}