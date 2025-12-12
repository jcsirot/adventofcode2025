import org.chelonix.aoc2025.day11Part1
import org.chelonix.aoc2025.day11Part2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun day11Part1() {
        val result = """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out            
        """.trimIndent().day11Part1()
        assertEquals("5", result)
    }

    @Test
    fun day11Part2() {
        val result = """
            svr: aaa bbb
            aaa: fft
            fft: ccc
            bbb: tty
            tty: ccc
            ccc: ddd eee
            ddd: hub
            hub: fff
            eee: dac
            dac: fff
            fff: ggg hhh
            ggg: out
            hhh: out            
        """.trimIndent().day11Part2()
        assertEquals("2", result)
    }
}