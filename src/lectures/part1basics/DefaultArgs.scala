package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = {
      if(n <= 1) acc
      else factorial(n-1, n * acc)
  }

  val fact10 = factorial(10)
  println(fact10)

  def savePicture(format: String = "jpg", width: Int= 1920, height: Int = 1080): Unit = println("Saving picture")


  savePicture("jpg", 800, 600)
  savePicture()
  savePicture(width = 800)

  savePicture(height = 100, format = "bitmap", width = 433)
}
