import java.util.*

fun main() {
    fun gcd(x: Int, y: Int): Int {
        if (y == 0) return x
        return gcd(y, x % y)
    }

    fun bfs(input: List<String>, t0: Int, sx: Int, sy: Int, ex: Int, ey: Int): Int {
        val r = input.size - 2
        val c = input[0].length - 2
        val r2 = r + 2
        val c2 = c + 2
        val lcm = c / gcd(r, c) * r

        fun toInt(tt: Int, xx: Int, yy: Int): Int {
            return tt % lcm * r2 * c2 + xx * c2 + yy
        }

        fun toTuple(vv: Int): Triple<Int, Int, Int> {
            return Triple(vv / (r2 * c2), vv / c2 % r2, vv % c2)
        }

        val q: Queue<Int> = LinkedList<Int>()
        val d = IntArray(lcm * r2 * c2) { -1 }
        val begin = toInt(t0, sx, sy)

        val ddx = intArrayOf(0, 0, 0, 1, -1)
        val ddy = intArrayOf(0, 1, -1, 0, 0)

        fun normal(x: Int, m: Int): Int {
            val rt = x % m
            return if (rt < 0) rt + m
            else rt
        }

        fun ok(tt: Int, xx: Int, yy: Int): Boolean {
            if (xx < 0 || xx >= r2) return false
            if (input[xx][yy] == '#') return false
            if (xx == 0 || xx == r2 - 1) return true
            // left
            if (input[xx][normal(yy + tt - 1, c) + 1] == '<') return false
            if (input[xx][normal(yy - tt - 1, c) + 1] == '>') return false
            if (input[1 + normal(xx + tt - 1, r)][yy] == '^') return false
            if (input[1 + normal(xx - tt - 1, r)][yy] == 'v') return false
            return true
        }

        d[begin] = t0
        q.add(begin)
        while (q.size > 0) {
            val id = q.remove()
            val (t, x, y) = toTuple(id)
            val ta = d[id]
            if (x == ex && y == ey) {
                return ta
            }

            for (i in 0..4) {
                val (nx, ny) = Pair(x + ddx[i], y + ddy[i])
                val nt = (t + 1) % lcm
                if (!ok(nt, nx, ny)) continue
                val nid = toInt(nt, nx, ny)
                if (d[nid] != -1) continue
                d[nid] = ta + 1
                q.add(nid)
            }
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        val r = input.size - 2
        val c = input[0].length - 2
        return bfs(input, 0, 0, 1, r + 1, c)
    }

    fun part2(input: List<String>): Int {
        val r = input.size - 2
        val c = input[0].length - 2
        val t1 = bfs(input, 0, 0, 1, r + 1, c)
        val t2 = bfs(input, t1, r + 1, c, 0, 1)
        return bfs(input, t2, 0, 1, r + 1, c)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day24_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 54)

    val input = readInput("Day24")
    part1(input).println()
    part2(input).println()
}
