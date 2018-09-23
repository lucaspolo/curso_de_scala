package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {
  val x: Any = "Scala"

  // 1 - Constants
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - Match anithing
  // 2.1 - wildcard

  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found something"
  }

  // 3 - Tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // PMs can be NESTED!

  // 4 - Case classes - constructor pattern
  // PMs can be nested with case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Cons(3)))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5 - List patterns
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // list of arbitraty length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 42 => // infix pattern
  }

  // 6 - Type specifiers
  val unknown: Any = 2
  val unknownMatching = unknown match {
    case list: List[Int] => // Explicit type specifier
    case _ =>
  }

  // 7 - Name binding
  val nameBindingMatch = aList match {
    case nonEmpty @ Cons(_, _) => // name binding => use the name later (here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8 - Multi-patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => // Compound pattern (multi-pattern)
    case _ =>
  }

  // 9 - If guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // ALL

  /*
    Question.
   */

  val numbers = List(1, 2, 3)
  val numbersMatching = numbers match {
    case listOfStrings: List[String] => "A list of strings"
    case listOfNumbers: List[Int] => "A list of numbers"
    case _ => ""
  }

  println(numbersMatching)

  // JVM Trick Question - The compiler erases generics types after compilation
}
