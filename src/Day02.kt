fun main() {
    fun part1(input: List<String>): Int {
        var score = 0
        for (it in input){
            val l = it[0]-'A'
            val r = it[2]-'X'
            when ((r-l+3)%3){
                0 -> score+=3
                1 -> score+=6
            }
            score += r + 1
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        for (it in input) {
            val l:Int = it[0]-'A'
            when (it[2]) {
                'Y' -> score += 3+l+1
                'X' -> score += (l+2)%3+1
                'Z' -> score += 6 + (l+1)%3+1
            }
        }
        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
