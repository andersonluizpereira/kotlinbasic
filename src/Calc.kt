fun main(args: Array<String>) {
 var num1 : Double
 var num2 : Double



    print("Enter number 1: ")
    num1 = readLine()!!.toDouble()

    print("What you need ? (+,-,*, /) ")
    val op:String = readLine()!!

    print("Enter number 2: ")
    num2 = readLine()!!.toDouble()

    var result = 0.0

    when(op){
        "+" -> result = num1 + num2
        "-" -> result = num1 - num2
        "*" -> result = num1 * num2
        "/" -> result = num1 / num2
        else -> println("Invalid operation")
    }

    println("$num1 $op $num2 = $result")

}