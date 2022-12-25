import kotlin.time.Duration.Companion.milliseconds

fun main() {

    val ddx = intArrayOf(-1, 1, 0, 0)
    val ddy = intArrayOf(0, 0, -1, 1)
    val offset = 2048
    val divide = 4096


    fun inputToArr(input: List<String>): List<Int> {
        val arr: MutableList<Int> = mutableListOf()
        for (i in input.indices) {
            for (j in input[0].indices) {
                if (input[i][j] == '#') arr += (i + offset) * divide + (j + offset)
            }
        }
        return arr
    }

    fun xy2i(x: Int, y: Int): Int {
        return x shl 12 or y
    }

    fun i2xy(i: Int): Pair<Int, Int> {
        return Pair<Int, Int>(i shr 12, i and 4095)
    }

    fun arrNext(arr: List<Int>, d0: Int): Pair<Int, List<Int>> {
        val result: MutableList<Int> = mutableListOf()
        val org = HashSet(arr)
        val nx = HashMap<Int, Int>()
        val nxc = HashMap<Int, Int>()

        for (i in arr) {
            val (x, y) = i2xy(i)
            var c = 0
            for (dx in -1..1) for (dy in -1..1) {
                if (org.contains(xy2i(x + dx, y + dy))) c++
            }
            if (c == 1) {
                result.add(i)
                continue
            }
            var hasnx = false
            for (d in 0..3) {
                val nd = (d + d0) and 3
                val dx = ddx[nd]
                val dy = ddy[nd]
                var ok = true
                if (dx == 0) {
                    for (td in -1..1) {
                        if (org.contains(xy2i(x + td, y + dy))) ok = false
                    }
                }
                if (dy == 0) {
                    for (td in -1..1) {
                        if (org.contains(xy2i(x + dx, y + td))) ok = false
                    }
                }
                if (ok) {
                    val nt = xy2i(x + dx, y + dy)
                    nx[i] = nt
                    if (nxc[nt] != null) nxc[nt] = nxc[nt]!! + 1
                    else nxc[nt] = 1
                    hasnx = true
                    break
                }
            }
            if (!hasnx) result.add(i)
        }

        var moves = 0
        for (i in arr) {
            val nt = nx[i]
            if (nt != null) {
                if (nxc[nt] == 1) {
                    result.add(nt)
                    moves++
                } else {
                    result.add(i)
                }
            }
        }

        return Pair(moves, result)
    }

    fun part1(input: List<String>): Int {
        var arr = inputToArr(input)
        for (i in 0..9) {
            val (_, arr2) = arrNext(arr, i)
            arr = arr2
        }

        var xx = intArrayOf()
        var yy = intArrayOf()
        for (i in arr) {
            val (x, y) = i2xy(i)
            xx += x
            yy += y
        }
        val ans = (xx.max() - xx.min() + 1) * (yy.max() - yy.min() + 1) - arr.size
        return ans
    }

    fun part2(input: List<String>): Int {
        var arr = inputToArr(input)
        var loop = 0
        while (true) {
            val (c, arr2) = arrNext(arr, loop)
            loop++
            if (c == 0) {
                return loop
            }
            arr = arr2
        }
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day23_test")
    check(part1(testInput) == 110)
    check(part2(testInput) == 20)

    val input = readInput("Day23")
    val t1 = System.currentTimeMillis().milliseconds
    part1(input).println()
    val t2 = System.currentTimeMillis().milliseconds
    println(t2 - t1)
    part2(input).println()
    val t3 = System.currentTimeMillis().milliseconds
    println(t3 - t2)
}
