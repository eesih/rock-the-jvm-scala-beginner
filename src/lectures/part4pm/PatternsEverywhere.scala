package lectures.part4pm

object PatternsEverywhere extends App {

  //Big idea #1

  try {
    //code
  } catch {
    case e : RuntimeException =>
    case npe : NullPointerException =>
    case _ =>
  }

  //catches are matches

  val list = List(1,2,3)
  val even = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  //Generators are also based on pattern matching

  val listOfTuples = List((1,2),(3,4))
  val filterTuples = for {
    (first, second) <- listOfTuples
  } yield first + second
  //case classes, :: operators

  //Big idea #3

  val tuple = (1,2,3)
  val (a, b, c) = tuple
  //multiple value definitions based on PM

  val head :: tail = list

  //Big idea #4
  //partial functions based on PM
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ = "something else"
  } //partial function literal

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ = "something else"
    }
  }

}
