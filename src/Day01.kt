import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        var mx = 0
        var sm = 0
        for (item in input) {
            if (item == ""){
                mx = max(mx,sm)
                sm = 0
            }else{
                sm += item.toInt()
            }
        }
        return max(sm, mx)
    }

    fun part2(input: List<String>): Int {
        var sm = 0
        var nums = arrayOf(0)
        for (item in input) {
            if (item == ""){
                nums += sm
                sm = 0
            }else{
                sm += item.toInt()
            }
        }
        nums += sm
        nums.sort()
        nums.reverse()
        return nums[0]+nums[1]+nums[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
