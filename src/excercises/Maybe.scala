package excercises

abstract class Maybe[+T] {
  def map[B](f: T => B): Maybe[B]
  def flatMap[B](f: T => Maybe[B]): Maybe[B]
  def filter(f: T => Boolean): Maybe[T]
}

case object MaybeNot extends Maybe[Nothing] {
  def map[B](f: Nothing => B) = MaybeNot
  def flatMap[B](f: Nothing => Maybe[B]) = MaybeNot
  def filter(f: Nothing => Boolean) = MaybeNot
}

case class Just[+T](value: T) extends Maybe[T] {
  def map[B](f: T => B) = new Just(f(value))
  def flatMap[B](f: T => Maybe[B]) = f(value)
  def filter(predicate: T => Boolean) =
    if (predicate(value)) this
    else MaybeNot
}

object MaybeTest extends App {

  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just( x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))

}