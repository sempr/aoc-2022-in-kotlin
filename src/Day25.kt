fun main() {

    fun s2l(s:String):Long {
        var x:Long = 0
        for (c in s){
            if (c=='\n') break
            x *= 5
            when (c) {
                '1' -> x+=1
                '2' -> x+=2
                '-' -> x-=1
                '=' -> x-=2
            }
        }
        return x
    }

    fun l2s(x:Long):String {
        var s = ""
        val ww = "012=-"
        var t=x
        while (t>0){
            val z = (t%5).toInt()
            s = ww[z] + s
            t -= if (z<=2) z
            else z-5
            t/=5
        }
        return s
    }

    fun part1(input: List<String>): String {
        var ans:Long = 0
        for (s in input) ans += s2l(s)
        return l2s(ans)
    }
    
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day25_test")
    check(part1(testInput) == "2=-1=0")

    val input = readInput("Day25")
    part1(input).println()
}
