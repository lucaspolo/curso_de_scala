package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // An expression
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  println(!(x == 1))

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  //Instructions (Do) vs Expressions (Calculate)

  // IF EXPRESSION
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)
  println(if(aCondition) 3 else 1)

  // THE USE OF LOOPS ARE DISCOURAGED
  var i = 0
  while(i < 10) {
    println(i)
    i+=1
  }

  //EVERYTHING in Scala is a Expression!

  val aWeirdValue = (aVariable = 3) // Type Unit === void
  println(aWeirdValue)

  //Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

  // val anotherValue = z + 1 // z cant be resolved

  // 1. difference between "hello world" vs println("hello world")
  // "hello world" is val: String and println("...") is a Expression Unit

  // 2.
  val someValue = {
    2 < 3
  }
  // The value is true

  // 3.
  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }

  // The value is 42 Int

}
