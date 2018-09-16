package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // Flatmap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')

  // iterating
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // foreach
  numbers.foreach((n) => chars.foreach((c) => println(n + "" + c)))
  println("==========================================================")
  // for-compreheinsions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
  } yield "" + c + n

  println(forCombinations)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    1. MyList supports for comprehensions?
      map(f: A => B) MyList[B]
      filter(p: A => Boolean) => MyList[A]
      flatMap(f: A => MyList[B]) => MyList[B]

      If have this methods de collection support for comprehensions

    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
   */


}
