package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is a charm"
    case _ => "Something else"
  }

  println(x)
  println(description)

  //1. Decompose values

  case class Person(name : String, age: Int)

  val bob = Person("Bob", 19)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hy my name is $n and I can't drink in US"
    case Person(n, a) => s"Hy my name is $n and I'm $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  sealed class Animal

  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal:Animal = Dog("Terra Nova")

  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    case Parrot(g) => println(s"Matched a Parrot")
  }

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr




  def show(e: Expr): String =
    e match {
      case Number(n) => s"$n"
      case Sum(expr1, expr2) => show(expr1) + "+" + show(expr2)
      case Prod(expr1, expr2) =>  {
        def maybeShowParentheses(exp: Expr) = exp match {
          case Prod(_,_) => show(exp)
          case Number(_) => show(exp)
          case _ => "(" + ")"
        }
        maybeShowParentheses(expr1) + " * " + maybeShowParentheses(expr2)
      }
  }

  val expr = Sum(Number(2), Number(3))
  println(show(expr))

}
