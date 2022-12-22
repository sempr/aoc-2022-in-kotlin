import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    fun gScore1(s: String): Int {
        val x = s.split(Regex("[,-]")).map { it.toInt() }
        val (l1, r1, l2, r2) = x
        if ((l1 <= l2 && r2 <= r1) || (l2 <= l1 && r1 <= r2)) {
            return 1
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        var count = 0
        for (it in input) count += gScore1(it)
        return count
    }

    fun gScore2(s: String): Int {
        val x = s.split(Regex("[,-]")).map { it.toInt() }
        val (l1, r1, l2, r2) = x
        val (l, r) = arrayOf(max(l1, l2), min(r1, r2))
        return if (l <= r) 1
        else 0
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (it in input) count += gScore2(it)
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
