import java.util.ArrayList

fun main() {
    println("Question 1")

    removeDupes(arrayListOf<Int>(1,4,3,1,2,4)).forEach { print("$it,") }

    println()
    println("Expected : (1,4,3,2)")
    println()

}

fun removeDupes(arrayList: ArrayList<Int>) : List<Int> {
    return arrayList.distinct()
}
