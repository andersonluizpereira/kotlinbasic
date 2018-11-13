import java.util.*

fun main(args: Array<String>){
    val name : String
    val input = Scanner(System.`in`)

    println("Enter your name: ")
    name = input.next()
//    name = readLine()!!
    println("How old are you ?")

    val age : Int = input.nextInt()

    println("My name is $name. I have $age years old and I have ${name.length} characters in my name")
    println(age)

}