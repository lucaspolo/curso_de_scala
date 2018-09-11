package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  // 1. Class parameters are fields
  val jim = Person("Jim", 10)
  println(jim.name)

  // 2. Sensible toString
  // println(instance) = println(instance.toString) // syntatic sugar
  println(jim)

  // 3. Equal and hashCode implemented out of the box
  val jim2 = new Person("Jim", 10)
  println(jim == jim2)

  // 4. Case Classes have handy copy method
  val jim3 = jim.copy()
  val joao = jim.copy(name = "João")

  // 5. Case classes have companion objectos
  val thePerson = Person
  val mary = Person("Mary", 23) // apply method

  // 6. Case classes are serializable
  // Akka needs

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
