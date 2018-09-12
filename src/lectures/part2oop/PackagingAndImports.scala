package lectures.part2oop

// Scala group the imports, and you can use aliases
import playground.{Cinderella => Princess, PrinceCharming}
// But you can import everything from package
// import playground._

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Jojo", "BAtata", 1990)

  // All the things are similar to Java

  // But we can declare functions, constants and other thins in the package object
  println(fatorial(10))

  val cinderella = new Princess
  val prince = new PrinceCharming

  // Default imports
  // java.lang - String, Exceptions...
  // scala - int, Nothing, Function
  // scala.Predef - println, ???
}
