package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  // This implementation can generate a stackoverflow
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n but I first need factorial of " + (n-1))
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")

      result
    }

  println(factorial(10))

  // Using tail-recursion for calculation
  // User recursive call as the LAST expression
  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator)

    factorialHelper(n, 1)
  }

  println(anotherFactorial(20000))

  //When you need loops, use tail recursion

  /*
    1. Concatenate a string n times
    2. isPrime function tail recursive
    3. Fibonacci numbers tail recursion
   */

  def concatenateStrings(s: String, n: Int): String = {
    @tailrec
    def concatenateStringsHelper(s: String, n: Int, accumlator: String): String = {
      if(n <= 0) accumlator
      else concatenateStringsHelper(s, n - 1, accumlator + s)
    }

    concatenateStringsHelper(s, n, "")
  }

  println(concatenateStrings("Lucas", 3))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean =
      if(!isStillPrime) false
      else if (t <= 1) true
      else isPrimeHelper(t - 1, n % t != 0 && isStillPrime)

    isPrimeHelper(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))

  def fibonacci(n: Int): Int = {
    def fibTail(i: Int, last: Int, nextToLast: Int): Int =
      if(i >= n) last
      else fibTail(i + 1, last + nextToLast, last)


    if (n <= 2) 1
    else fibTail(2, 1, 1)
  }

  println(fibonacci(8))
}
