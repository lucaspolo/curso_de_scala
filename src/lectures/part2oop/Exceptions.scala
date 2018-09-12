package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  // println(x.length)
  // this ^^ will crash with a NullPointer

  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extends the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42

  // The type of potentialFail will be AnyValue
  // because the compuler try unify the try: Int e catch: Unit
  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a runtime exception")
  } finally {
    // code that will execute not matter what
    // finally doesn't influence the return of this expression
    println("finally")
  }

  // The potentialFail2 will be int
  val potentialFail2 = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 2
  } finally {
    // code that will execute not matter what
    println("finally")
  }

  // 3. how to define your own exceptions

  class MyException extends Exception
  val exception = new MyException

  // throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with StackOverflowError
    3. PocketCalculator
      - add(x,y)
      - substract(x,y)
      - multiply(x,y)
      - divide(x,y)
   */

  // val array = Array.ofDim(Int.MaxValue)
  // def func(): Int = 1 + func()
  // func()

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      x + y
    }
  }
}
