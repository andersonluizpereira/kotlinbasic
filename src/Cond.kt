fun main(args : Array<String>) {
    val num1 : Int = 5
    val num2 : Int = 20

    val max: Int = if (num1 > num2) {
        print("Helllo from block")
        num1
    } else {
        println("Helllo from block 2")
        num2
    }

    println(max)

    if (num1 > num2) {
        println("Number 1 maior")
    } else {
      println("Number 1 menor")
    }

    when(num1) {
        1 -> println("1")
        2 -> println("2")
        10 -> println("10")
        else -> {
            println("No options")
        }
    }

}
