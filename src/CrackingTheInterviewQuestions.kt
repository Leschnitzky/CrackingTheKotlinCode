fun main(){
    print("First Questions Tests:")
    println()
    arrayOf("abc","abbc","","aAbBcC","bBBc").map{
        isUnique(it)
    }.forEach { print("$it,") }
    println()
    print("Second Questions Tests:")
    println()
    val secondQuestionArray = arrayOf("abc","","aabbcc","AbC")
    val secondQuestionArrayTwo = arrayOf("bac","","bbbbaacc","CAb")
    secondQuestionArray.mapIndexed{
            index, s ->
            isPermutation(s,secondQuestionArrayTwo[index])
        }.forEach { print("$it,") }
    println()
    print("Third Questions Tests:")
    println()
}


// Question 1
fun isUnique (string: String) : Boolean{
    val letterMap = HashMap<Char,Int>()

    for (letter in string){
       if (letterMap.containsKey(letter)) {
           return false
       } else {
           letterMap[letter] = 1
       }
    }
    return true
}

// Question 2
fun sortString(s1: String) : String = s1.toCharArray().sorted().joinToString()

fun isPermutation(s1: String, s2: String) : Boolean{
    return sortString(s1) == sortString(s2)
}