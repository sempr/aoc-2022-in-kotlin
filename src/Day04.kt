import java.lang.Integer.min
import java.lang.Integer.max

fun main() {
    fun gScore1(s:String):Int{
        val ss = s.split(",")
        val aa=ss[0].split("-")
        val bb=ss[1].split("-")
        val a0=aa[0].toInt()
        val a1=aa[1].toInt()
        val b0=bb[0].toInt()
        val b1=bb[1].toInt()
        if (a0<=b0 && b1<=a1) {
            return 1
        }
        if (b0<=a0 && a1<=b1){
            return 1
        }
        return 0
    }
    fun part1(input: List<String>): Int {
        var count = 0
        for (it in input) count += gScore1(it)
        return count
    }

    fun gScore2(s:String):Int {
        val ss = s.split(",")
        val aa=ss[0].split("-")
        val bb=ss[1].split("-")
        val a0=aa[0].toInt()
        val a1=aa[1].toInt()
        val b0=bb[0].toInt()
        val b1=bb[1].toInt()
        val l=max(a0,b0)
        val r=min(a1,b1)
        return if (l<=r) 1
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
