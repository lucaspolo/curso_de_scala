package lectures.part2oop

object OOBasics extends App {
  val writer = new Writer("Juca", "Jucoso", 1945)

  println(writer.fullname())

  val novel = new Novel("Budis Amiguinhos", 1985, writer)
  println(novel.authorAge())
  println(novel.isWrittenBy(writer))
  println(novel.copy(2018))

  var counter = new Counter(10)
  println(counter.dec(5))
}

//constructor
class Person(name: String, val age: Int) {
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  //overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // overloading constructors

  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

/*
  Novel and Writer
  Writer: First name, surname, year
    - method fullname

   Novel: Name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
 */

class Writer(val firstName: String, val surname: String, val year: Int) {
  def fullname(): String = f"${this.firstName} ${this.surname}"
}

class Novel(val name: String, val year: Int, var author: Writer) {
  def authorAge(): Int = this.year - author.year

  def isWrittenBy(author: Writer): Boolean = this.author == author

  def copy(year: Int): Novel = new Novel(this.name, year, this.author)
}

class Counter(val count: Int) {
  def inc = new Counter(count + 1)
  def dec = new Counter(count - 1)

  // We can use recursion (or just subtract)
  def inc(times: Int): Counter =
    if (times <= 0) this
    else inc.inc(times - 1)

  def dec(times: Int): Counter =
    if (times <= 0) this
    else dec.dec(times - 1)

  override def toString: String = f"Counter(${this.count})"
}

// Class parameters are not fields