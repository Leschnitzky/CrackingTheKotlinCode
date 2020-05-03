import kotlin.math.absoluteValue
import kotlin.text.StringBuilder

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
    println()
    print("Fifth Questions Tests:")
    println()
    val firstArray = arrayOf("","","","test","test","test","test")
    val secondArray = arrayOf("","a","ba","test","tets","est","tests")

    firstArray.mapIndexed {
        index, s ->
        oneEditAway(s,secondArray[index])
    }.forEach {
        print("$it,")
    }
    println()
    println("Expected: true,true,false,true,false,true,true")
    println()
    print("Sixth Questions Tests:")
    println()

    arrayOf("aaaAAAB","aBBbCCC","BbBbBBbBbb","","BaAAAAbbb","bbBBbbccc","bb").mapIndexed {
            index, s ->
        stringCompression(s)
    }.forEach {
        print("$it,")
    }
    println()
    println("Expected: a3A3B,aB2bC3,BbBbBBbBbb,,BaA4b3,b2B2b2c3,bb")
    println()

    val matrix= arrayOf(arrayOf(1,2,3),
             arrayOf(4,5,6),
             arrayOf(7,8,9))
    println(rotateMatrix(matrix))
    println()
    println("Expected: [[7,4,1], [8,5,2], [9,6,3]]")
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


//Question 5
fun oneEditAway(s: String, s2: String): Boolean {
    if((s.length - s2.length).absoluteValue > 1) return false
    if (s == s2) return true

    if(isRemoveEdit(s,s2) or isAdditionEdit(s,s2) or isChangeEdit(s,s2)) return true
    return false
}

fun isChangeEdit(s: String, s2: String): Boolean {
    if(s.length != s2.length) return false
    var numOfChanges = 0
    s.forEachIndexed {
        index, c ->
        if(c != s2[index]) numOfChanges += 1
    }
    return numOfChanges == 1
}

fun isRemoveEdit(s: String, s2: String): Boolean {
    if(s.length - s2.length != 1) return false

    s2.forEachIndexed { index, c ->
        if(c != s[index])
        { if (c != s[index + 1]) return false}
    }
    return true
}

fun isAdditionEdit(s: String, s2: String): Boolean {
    return isRemoveEdit(s2,s)
}


// Question 6

fun stringCompression(s:String) : String {
    val to_ret = kotlin.text.StringBuilder()
    var prev_letter : Char = '%'
    var counter = 1
    s.forEachIndexed{ index,let ->
        if (prev_letter == '%') {
            prev_letter = let
        }
        else if( let == prev_letter ){
            counter++
            if (index == s.length - 1){
                to_ret.append(prev_letter)
                to_ret.append(counter)
            }
        } else {
            to_ret.append(prev_letter)
            if(counter == 1) {
                prev_letter = let
            }
            else {
                to_ret.append(counter)
                counter = 1
                prev_letter = let
                if (index == s.length - 1) {
                    to_ret.append(prev_letter)
                }
            }
        }
    }
    return if (to_ret.length == s.length) s else to_ret.toString()
}

// Question 7

fun rotateMatrix(matrix: Array<Array<Int>>): Array<Array<Int>>? {
    return null
}

