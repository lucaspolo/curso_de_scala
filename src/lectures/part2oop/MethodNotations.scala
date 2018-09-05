package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == this.favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    // looks like method overloading
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(this.name + s" ($nickname)", this.favoriteMovie)

    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def unary_! : String = s"$name, what the heck?!"

    def isAlive: Boolean = true

    // Its like __call__
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): Unit = println(s"$name watched $favoriteMovie $n time(s)")

    def learns(subject: String): Unit = println(s"$name learns $subject")
    def learnsScala() = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation or operator notation (syntactic sugar)

  // "operators" in Scala

  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)

  // ALL mathematics operators are methods
  println(1.+(2))

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-

  // unary_ prefix only works with - + ~ !
  println(!mary)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())

  /*
    1. Overload the + operator
    mary + "the rockstar" => new Person "Mary (the Rockstar)"
   */

  println((mary + "The Rockstar").name)

  /*
    2. Add an age to the Person class
       Add a unary + operator => new person with the age + 1
       +mary => mary the age incrementer

       ++
   */

  println((+mary).age)

  /*
    3. Add a learns method in the Person class = Mary learns Sca

   */

  mary learnsScala

  mary(5)
}
