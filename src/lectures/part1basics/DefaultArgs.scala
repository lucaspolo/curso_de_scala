package lectures.part1basics

object DefaultArgs extends App {

  // Default values
  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n - 1, n * acc)

  println(trFact(10))
  println(trFact(5))

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")
  //savePicture(800)

  /*
    There is two solutions for the problem:
    1. pass in every leading argument
    2. Or we can name the arguments
   */
  savePicture(width = 800) // With this you can pass the arguments in any order.
}
