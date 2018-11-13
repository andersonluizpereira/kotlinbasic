fun main(args : Array<String>){
    println("Weight :")
    val weight = readLine()!!.toDouble()
    print("Height: ")
    val height = readLine()!!.toDouble()

    checkImc(weight,height)
}

fun checkImc(weight: Double, height: Double) {
 var result = weight / (height * height)

    if (result >=18 && result <= 25){
        println("Overweigth")
    }
    else{
        println("Normal Weight")
    }

}
