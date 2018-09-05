package lectures.part2oop

object AbstractDataTypes extends App {
  // abstract members

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat: Unit = println("crunch crunch")
  }

  val dog = new Dog
  dog.eat

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and a eating ${animal.creatureType}")
  }

  val croc = new Crocodile
  croc.eat(dog)

  // treats vs abstract class

  //abs class can have both abstract and non abs methods, traits too
  //traits can not have constructor parameters and you can mix multiple treats
  // treat are behavior, abstract are type
}
