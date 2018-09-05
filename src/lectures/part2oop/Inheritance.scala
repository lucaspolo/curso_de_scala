package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  //Look the way of the super constructor is called
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding

  class Dog(val specie: String) extends Animal {
    override val creatureType: String = specie
    override def eat = {
      super.eat
      println("Crunch Crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // You can override in constructor too

  class Gato(override val creatureType: String) extends Animal

  val gato = new Gato("domestic")
  println(gato.creatureType)

  class Budi(val tipo: String) extends Animal {
    override val creatureType: String = tipo
  }


  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overriding vs overloading (its not the same)

  //super

  // preveting overrides
  // 1 - Use keyword final on member
  // 2 - User final in Class
  // 3 - seal the class - extends class in THIS FILE, prevent extension in other files two
}
