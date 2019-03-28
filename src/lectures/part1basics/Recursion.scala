package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int) : Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  def anotherFactorial(n: Int) : BigInt = {
    @tailrec //TAIL RECURSION = use recursive call as the LAST expression
    def factHelper(x : Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)
    }
    factHelper(n, 1)
  }

  println(anotherFactorial(10))

  //WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

  //excercises

  def helloNTimes(n: Int): String = {
    @tailrec
    def helper(x: Int, acc: String) : String = {
      if(x <= 0) acc
      else helper(x - 1, acc + "hello")
    }
    helper(n, "")
  }

  println(helloNTimes(3))

  @tailrec
  def concanateTailrec(aString: String, n: Int, accumulator: String): String =
    if(n <= 0) accumulator
    else concanateTailrec(aString, n-1, aString + accumulator)

  println(concanateTailrec("hello", 4, ""))

  def isPrimeCorrect(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(x: Int, isStillPrime: Boolean): Boolean = {
      if(!isStillPrime) false
      else if(x <= 1) true
      else isPrimeTailRec(x - 1, n % x != 0 && isStillPrime)
    }
    isPrimeTailRec(n / 2, true)
  }
//10
  println(isPrime(10))
  println(isPrime(2003))
  println(isPrime(629))

  def fibonacci(n: Int): Int = {
    if(n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  def fibonacciTailRec(n: Int, acc: Int): Int = {
      if(n <= 2) acc
      else fibonacciTailRec(n-1, acc + n)
  }

  def fibonacci2(n: Int) : Int = {
    def fiboTailRec(i: Int, last: Int, nextLast: Int): Int = {
      if (i >= n) last
      else fiboTailRec(i + 1, last + nextLast, last)
    }
    if (n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }

  println(fibonacci(10))
  println(fibonacci2(10))


}
