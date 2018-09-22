package lectures.part4pm

import scala.util.Random

object PatternMatching extends App{
  // swtich on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "The ONE"
    case 2 => "Double or nothing"
    case 3 => "Third time is the charm"
    case _ => "Something else" // _ = WILDCARD
  }

  println(x, description)

  // 1. Decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am $a years old, I can't drive" // GUARD (Guarda)
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
    1. Case are matched in order
    2. What if no cases match? MatchError exception!
    3. What is the type of PM expression? Unified type of all the types in all the cases?
    4. PM works really well with case classes*
   */

  // PM on sealed hierarchyes
  sealed class Animal
  case class Dog(bread: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // Why?
  val isEvenCond = if (x % 2 == 0) true else false // ?!
  val isEvenNormal = x % 2 == 0

  /*
    Exercise

    Create a simple function that uses PM
      Takes an Expr and returns human readable form

    Ex:
      Sum(Number(2), Number(3)) => 2 + 3
      Sum(Number(2), Number(3), NUmber(4)) => 2 + 3 + 4
      Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
      Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n)    => s"$n"
    case Sum(e1, e2)  => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParentheses(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }

      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show((Prod(Sum(Number(2), Number(1)), Number(3)))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
}
