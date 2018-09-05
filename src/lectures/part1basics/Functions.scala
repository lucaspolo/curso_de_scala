package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("Lucas", 28))

  // Its amazing
  def aParameterlssFunction(): Int = 42

  println(aParameterlssFunction)

  // Recursion
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("Hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION.

  // The compiler can infer the return type of a function

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  aFunctionWithSideEffects("Lucas")

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(5))

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function
    3. Fibonacci Function
    4. Tests if a number is prime
   */
  def greetingForKids(name: String, age: Int): Unit = println(s"Hi, my name is $name and I am $age years old.")

  greetingForKids("Roli", 99999)

  def factorial(n: Int): Int = if (n == 1) 1 else n * factorial(n - 1)

  println(factorial(5))

  def fibonacci(n: Int): Int =
    if (n <= 2) 1 else fibonacci(n -1 ) + fibonacci(n - 2)

  println(fibonacci(6))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(5))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
