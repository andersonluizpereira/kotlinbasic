fun main(args : Array<String>) {
    val arr : Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (item in arr) {
        println(item)
    }

    val map = mutableMapOf<Int, Any>()

//Java Style
    map.put(1, "Leonan")
//Programming
    map[2] = "Luppi"
//Kotlin
    map += 3 to "21"

    for (item in map) {
        println(item.key)
        println(item.value)
    }
}