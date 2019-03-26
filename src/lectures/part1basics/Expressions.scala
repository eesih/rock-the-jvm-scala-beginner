package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 //expressions
  println(x)

  println(2 + 3 * 4)
  //+ - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  //== != > >= < <=

  println(!(1 == x))
  //! && ||

  var aVariable = 2
  aVariable += 3 //also works with -= *= /= ==side effects
  println(aVariable)

  //instructions (DO) vs expressions (VALUE)

  //If expression

  val aCondition = true
  val aConditionedValue = if(aCondition) 15 else 3
  println(aConditionedValue)

  var i = 0

  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }

  //NEVER WRITE THIS AGAIN.

  //everything in scala is an expression!

  val aWeirdValue = (aVariable = 3)
  println(aWeirdValue)

  val aCodeBlock = {
    val y = 2
    val c = y +2
    if(c > 2) "Hello" else "Goodbye"
  }


}
