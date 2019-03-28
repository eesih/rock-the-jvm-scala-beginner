package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello", 3))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("Hello", 3))

  //WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(s"hello $aString")

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n-1)
  }

  //excercises
  def greeting(name: String, age: Int) = s"Hello, my name is $name and I'm $age years old."

  println(greeting("Eero", 39))

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n-1)
  }

  def fibonacci(n: Int): Int = {
    if(n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  def isPrime(n: Int):Boolean = {
    (2 to n) forall (x => n % x != 0)
  }

  def isPrimeCorrect(n: Int):Boolean = {
    def isPrimeUntil(t: Int) : Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t -1)
    }

    isPrimeUntil(n / 2)
  }

  println(factorial(5))
  println(fibonacci(8))
  //println(isPrime(9))
  //println(isPrime(11))

  println(isPrimeCorrect(3))
  println(isPrimeCorrect(37))
  println(isPrimeCorrect(2003))
  println(isPrimeCorrect(37 * 17))

}
