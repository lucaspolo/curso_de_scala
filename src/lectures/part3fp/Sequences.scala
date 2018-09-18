package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  val aSequence = Seq(1,4,2,3)
  println(aSequence)

  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,8,7))

  println(aSequence.sorted)

  // Ranges
  var aRange: Seq[Int] = 1 until 10 // not inclusive 10
  aRange = 1 to 10 // inclusive 10

  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  //lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList // prepend
  println(prepended)

  val prepend2 = 42 +: aList :+ 84 // prepend(42) append(84)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // Arrays
  val numbers = Array(1, 2, 3, 4)
  println(numbers)

  val treeElements = Array.ofDim[String](3)
  treeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)

  println(numbers.mkString(" "))

  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs list

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps referente to tail
  // update in the middle takes a long time
  println(getWriteTime(numbersList))

  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
