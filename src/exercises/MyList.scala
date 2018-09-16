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

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  // High order functions
  def foreach(f: (A) => Unit): Unit
  def sort(comparsion: (A, A) => Int): MyList[A]
  def zipwith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(comparsion: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipwith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have same length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A] = Empty) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if(t.isEmpty) "" + h
    else h + ", " + t.printElements

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {

    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) < 0 ) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipwith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), tail.zipwith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
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

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3)))
  val cloneOfListOfIntegers = new Cons(1, new Cons(2, new Cons(3)))
  println(listOfIntegers)
  println(listOfIntegers.add("Oloco"))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala"))

  var newList = listOfIntegers.map(_ * 2)

  println(newList)

  newList = listOfIntegers.filter(_ % 2 == 1)

  println(newList)

  newList = listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1)))
  println(newList)

  println(cloneOfListOfIntegers == listOfIntegers)

  listOfIntegers.foreach(println)

  listOfIntegers.sort((x, y) => y - x).foreach(println)

  listOfIntegers.zipwith[Int, Int](cloneOfListOfIntegers, _ + _).foreach(println)

  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string

  println(combinations)
}