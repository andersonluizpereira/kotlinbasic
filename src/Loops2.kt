fun main(args : Array<String>) {
    var n: Int = 0

    while (n <= 10) {
        println(n)
        n++
    }

    do {
        n++
        println(n)
    } while (n <= 10)
}
