fun main() {
    fun gScore1(s: String): Int {
        val l = s.length
        for (i in 0 until l / 2) {
            for (j in l / 2 until l) {
                if (s[i] == s[j]) {
                    return if (s[i] in 'A'..'Z') {
                        s[i] - 'A' + 27
                    } else {
                        s[i] - 'a' + 1
                    }
                }
            }
        }

        return 0
    }

    fun part1(input: List<String>): Int {
        var score = 0
        for (it in input)
            score += gScore1(it)
        return score
    }

    fun gScore2(a: String, b: String, c: String): Int {
        for (x in 'a'..'z') {
            if (a.contains(x) && b.contains(x) && c.contains(x)) {
                return x - 'a' + 1
            }
        }
        for (x in 'A'..'Z') {
            if (a.contains(x) && b.contains(x) && c.contains(x)) {
                return x - 'A' + 27
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        var score = 0
        for (i in input.indices step 3) {
            score += gScore2(input[i], input[i + 1], input[i + 2])
        }
        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
