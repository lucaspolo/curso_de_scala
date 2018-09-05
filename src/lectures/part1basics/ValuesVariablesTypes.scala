package lectures.part1basics

object ValuesVariablesTypes extends App {

  //val are immutable
  val x: Int = 42
  println(x)

  //The compiler can infer the type of a variable
  val y = 21
  println(y)

  //Semi-colon are optional and its use is discouraged
  //One command per line
  val aString: String = "Hello"; val anotherString = "Bye"

  val aBoolean: Boolean = true //or false
  println(aBoolean)

  val aChar: Char = 'l'
  println(aChar)

  val anInt: Int = x
  print(anInt)

  val aShort: Short = 3413
  print(aShort)

  val aLong: Long = 123214223423423L
  print(aLong)

  val aFloat: Float = 2.0f
  print(aFloat)

  val aDouble: Double = 3.14
  println(aDouble)

  // To define a variable, we can use the keyword var
  var aVariable: Int = 4
  aVariable = 5          //This will produces side effects
  println(aVariable)
}
