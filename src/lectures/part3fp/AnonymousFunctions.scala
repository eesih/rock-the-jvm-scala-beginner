package lectures.part3fp

object AnonymousFunctions extends App{

  //anonymous function (LAMBDA)
  val doubler: Int => Int = (x) => x * 2

  //multiple parameters
  val added = (a: Int, b: Int) => a + b

  val adder2: (Int, Int) => Int = (a, b) => a + b

  //no params
  val justDoSomething = () => "hello"
  println(justDoSomething) //function itself
  println(justDoSomething()) //call the function

  //curly braces with the lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  //MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 //equivalent to x  => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ //equivalent to (a,b) => a + b



}
