package lectures.part3fp

object HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // map, flatmap and filter in MyList

  // function that applies a function n times over a valueX
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

//  val plusOne = (x: Int) => x + 1

  println(nTimes(_ + 1, 5, 5))

  // ntb(f,n) = x => f(f(f...(x)))
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n < 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))

  val plus10 = nTimesBetter(_ + 10, 10)
  println(plus10(5))

  // Curried functions
  val supperAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = supperAdder(3)

  println(add3(10))
  println(supperAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c:String)(x:Double):String = c.format(x)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
