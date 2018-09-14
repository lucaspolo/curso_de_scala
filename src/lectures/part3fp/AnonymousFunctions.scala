package lectures.part3fp

object AnonymousFunctions extends App {

  // A little verbose
  val doubler = new Function[Int, Int] {
    override def apply(v1: Int): Int = 2 * v1
  }

  // A better (and beautiful) way
  // val doubler2 = (x: Int) => x * 2 // anonymous function or LAMBDA λ
  // val doubler2: Int => Int = (x: Int) => x * 2 // anonymous function or LAMBDA λ
  val doubler2: Int => Int = x => x * 2 // anonymous function or LAMBDA λ

  // multiple parameters in a lambda
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething = () => 3

  println(justDoSomething)
  println(justDoSomething())

  // curly braces
  val stringToInt = { (str:String) =>
    str.toInt
  }

  // MORE syntatic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  println(niceAdder(5,4))

  /*
    1. Go to MyList and replace all FunctionX call with lambdas
    2. Define the "special" adder as anonymous function
   */

  val superAdd = (x: Int) => (y: Int) => x + y

  println(superAdd(6)(9))
}
