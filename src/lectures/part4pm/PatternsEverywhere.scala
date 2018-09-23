package lectures.part4pm

object PatternsEverywhere extends App {
  // Big Idea #1

  try {

  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // Catches are actually matches
  /*
    try {

    } case (e) {
      e match {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "npe"
        case _ => "something else"
      }
    }
   */

  // Big Idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // Generator are also based on Pattern Matching
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // Case classes, :: operator, ...

  // Big Idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  // Multiple value definitions based on PM
  // ALL THE POWER

  val head :: tail = list
  println(head)
  println(tail)

  // Big Idea #4
  // Partial Function

  // Partial Function
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  }

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

  println(mappedList)


}
