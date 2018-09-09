package exercises

abstract class MyList[+A] {
  /*
    head = First element
    tail = remainder of the list

    isEmpty = is this list empty
    add(int) => new list with this element add
    toString => a string represation
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A] = Empty) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if(t.isEmpty) "" + h
    else h + ", " + t.printElements

  override def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}


/*
  1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2. Generic trait MyTransform[-A, B] with a method transform(A) => B
  3. MyList:
    - map(transform) => MyList
      [1,2,3].map(n * 2) = [2,4,6]
    - filter(predicate) => MyList
      [1,2,3,4].filter(n % 2 == 0) = [2,4]
    - flatMap(transformer from A to MyList[B]) => MyList[B]
      [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
 */

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object ListTest extends App {
  val listOfIntegers: MyList[Integer] = new Cons(1, new Cons(2, new Cons(3)))
  println(listOfIntegers)
  println(listOfIntegers.add("Oloco"))
  val listOfStrings: MyList[String] = Empty

  var newList = listOfIntegers.map(new MyTransformer[Integer, Integer] {
    override def transform(element: Integer): Integer = {
      element * element
    }
  })

  println(newList)

  newList = listOfIntegers.filter(new MyPredicate[Integer] {
    override def test(element: Integer): Boolean = element % 2 == 1
  })

  println(newList)

  newList = listOfIntegers.flatMap(new MyTransformer[Integer, MyList[Integer]] {
    override def transform(element: Integer): MyList[Integer] = new Cons(element, new Cons(element + 1))
  })
  println(newList)
}