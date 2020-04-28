fun main(){
    print("First Questions Tests:")
    println()
    arrayOf("abc","abbc","","aAbBcC","bBBc").map{
        isUnique(it)
    }.forEach { print("$it,") }
    println()
    println("Expected: true,false,true,true,false")
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
    println("Expected: true,true,false,true")
    println()
    print("Third Questions Tests:")
    println()

    arrayOf(""," ","A B C", "B  A", " BB A", " B ").map {
        urlifyString(it)
    }.forEach { print("$it,") }
    println()
    println("Expected: ,%20,A%20B%20C,B%20%20A,%20BB%20A,%20B%20")
    println()
    print("Fourth Questions Tests:")
    arrayOf(""," ","realaer", "with      tiw", "ppeerrt", "noperm").map {
        isPalindromePermutation(it)
    }.forEach { print("$it,") }
    println()
    println("Expected: true,true,true,true,true,false")
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

// Question 3
fun urlifyString(str: String) : String {
    return str.replace(" ","%20")
}

// Question 4
fun isPalindromePermutation(str: String): Boolean {
    val allPermutations = getPermutations(str)
    if(allPermutations.isEmpty())
        return true
    for (permutation in allPermutations){
        if(isPalindrome(permutation)){
            return true
        }
    }

    return false
}

fun getPermutations(str: String): List<String> {
    var permuteString = str.replace("\\s".toRegex(), "")

    val permutations = ArrayList<String>()
    permute(permuteString,"", permutations)
    return permutations
}

fun permute(
    str: String,
    ans: String,
    permutations: ArrayList<String>
) {
        var permuteStr = str
        if (permuteStr.isEmpty())
            permutations.add(ans)
        else
        {
            for (i in permuteStr.indices)
            {
                var ch: Char = permuteStr[i]

                // Rest of the string after excluding
                // the ith character
                var ros = permuteStr.substring(0, i) +
                        permuteStr.substring(i + 1)
                var rest = ans + ch
                permute(ros, rest,permutations)
            }
        }
}
fun isPalindrome(str :String): Boolean {
    if(str.isEmpty() || str.length == 1){
        return true
    }
    val firstPart = str.slice(0 until str.length/2)
    var secondPart = ""
    if (str.length % 2 == 0){
        secondPart = str.slice(str.length/2 until str.length)
    } else {
        secondPart = str.slice(str.length/2 + 1 until str.length)
    }

    val reversedSecondPart = secondPart.reversed()

    return firstPart == reversedSecondPart
}
