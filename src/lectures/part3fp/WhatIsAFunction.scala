package lectures.part3fp

object WhatIsAFunction extends App {

  // DREAM: user functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = {
      element * 2
    }
  }

  println(doubler(2))

  // function types = Function[A,B]

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = {
      v1 + v2
    }
  }

  println(adder(5,4))

  // Function types: Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTION ARE OBJECTS

  /*
    1. Define a function which takes 2 strings and concatenates then
    2. Transform the MyPredicate and MyTransformer into function Types
    3. Define a function which takes an int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
   */

  val conc = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(conc("Big", "Potato"))

  val func1 = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = {
      new Function1[Int, Int] {
        override def apply(v2: Int): Int = {
          v1 + v2
        }
      }
    }
  }

  val r1 = func1(5)
  val r2 = r1(6)
  println(r2)
  println(func1(4)(90)) // curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
