package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  //seq
  val aSeq = Seq(1,2,4,3)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))

  println(aSeq ++ Seq(5,6))
  println(aSeq.sorted)


  //Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  //lists
  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))


  //arrays
  val numbers = Array(1,2,3,4)
  val treeElements = Array.ofDim[String](3)
  treeElements.foreach(println)

  //mutation
  numbers(0) = 1 //syntax sugar for update method numbers.update(0,1)
  println(numbers.mkString(" "))

  //arrays and seqs
  val numbersSeq: Seq[Int] = numbers //implicit conversion

  println(numbersSeq)

  //vector
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)


  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt)
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  // keeps reference to a tail
  //updating an element in the middle takes long
  val numbersList = (1 to maxCapacity).toList

  //depth of the tree is small
  //needs to replace an entire 32-element chunk
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
}
