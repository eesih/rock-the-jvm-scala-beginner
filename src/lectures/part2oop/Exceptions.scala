package lectures.part2oop

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object Exceptions extends App {

  val x: String = null
  //println(x.toString)


  //val aWeirdValue = throw new NullPointerException

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("My exception")
    else 42


  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    println("finally")
  }

  println(potentialFail)

  class MyException extends Exception
  val myException = new MyException

  //throw myException

  //val array = Array.ofDim(Int.MaxValue) //OutOfMemoryError


  def infinite: Int = 1 + infinite //StackOverflowError

 // infinite


  println(new PocketCalculator().add(10, Int.MaxValue))


  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException

  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val res = x + y
      if (x > 0 && y > 0 && res < 0) throw new OverflowException
      else if (x < 0 && y < 0 && res > 0) throw new UnderflowException
      else res
    }
  }



}
