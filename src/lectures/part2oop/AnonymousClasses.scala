package lectures.part2oop

object AnonymousClasses extends App {
  trait Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = {
      println("Ahahahahahaha")
    }
  }

  println(funnyAnimal.getClass)

  class Person(name:String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val lucas = new Person("Lucas")
  lucas sayHi

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"My name is Jim")
  }

  jim sayHi

  println(jim.getClass)
}
