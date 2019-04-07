package lectures.part4pm

import excercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

  //1. constants
  val x: Any = "Scala"

  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE SCALA"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }

  //2. Match anything
  //2.1 wildcards

  val matchAnything = x match {
    case _ => "wildcard"
  }

  //2.2 variable
  val matchAVariable = x match {
    case something => s"I have found $something"
  }

  //3. Tuples
  val aTuple = (1,2)

  val matchATuple = aTuple match {
    case (1,1) =>
    case (something, 2) => s"I have found $something"
  }

  val nestedTuple =(2,(2,3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => s"found tuple with value $v"
  }

  //4. Case classes - constructor patters
  //PMs can be nested with CCs as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  //5. List patters
  val aStandartList = List(1,2,3,42)
  val standartListMathing = aStandartList match {
    case List(1,_,_,_) => //extractor
    case List(1, _*) => //List of arbitrary length
    case 1 :: List(_) => //Infix pattern
    case List(1,2,3) :+ 42 => //infix pattern
  }

  //6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //Explicit type specifier
    case _ =>
  }

  //7 name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => //name binding => use the same name here
    case Cons(1, rest $ Cons(2, Empty)) => //name binding
  }

  //8 multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => //compound pattern (multi-pattern)
  }

  //9 if guards
  val secondElement = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => //if guard
  }

}
